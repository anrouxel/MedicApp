package fr.medicapp.medicapp.ai

import android.content.Context
import android.content.res.AssetManager
import android.net.Uri
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.annotation.WorkerThread
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import fr.medicapp.medicapp.tokenization.Feature
import fr.medicapp.medicapp.tokenization.FeatureConverter
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.Tensor
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.CountDownLatch

class PrescriptionAI(
    val context: Context
) {
    private var mModule: Module? = null
    private lateinit var mBackgroundThread: HandlerThread
    private lateinit var mHandle: Handler

    private val TAG = "CamemBERT"
    private val MODEL_PATH = "model.pt"
    private val DIC_PATH = "vocab.txt"
    private val LABEL_PATH = "id_to_label.txt"
    private val dic: HashMap<String, Int> = HashMap()
    private val labels: HashMap<Int, String> = HashMap()
    private lateinit var featureConverter: FeatureConverter
    private val MAX_ANS_LEN = 512
    private val MAX_QUERY_LEN = 512
    private val MAX_SEQ_LEN = 512
    private val DO_LOWER_CASE = false
    private val PREDICT_ANS_NUM = 5
    private val NUM_LITE_THREADS = 4

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

    init {
        mBackgroundThread = HandlerThread("BackgroundThread")
        mBackgroundThread.start()
        mHandle = Handler(mBackgroundThread.looper)
        mHandle.post {
            loadModel()
        }
    }

    fun analyse(
        imageUri: Uri,
        onPrediction: (MutableList<Pair<String, String>>) -> Unit,
        onDismiss: () -> Unit
    ) {
        mHandle.post {
            while (mModule == null) {
                Thread.sleep(100)
            }
            val visionText = recognizeText(imageUri)
            if (visionText != null) {
                val sentenceTokenized = runModel(visionText)
                onPrediction(sentenceTokenized)
            }
            onDismiss()
        }
    }

    @WorkerThread
    private fun recognizeText(imageUri: Uri): String? {
        var visionText: String? = null
        val latch = CountDownLatch(1)
        val image = InputImage.fromFilePath(context, imageUri)
        recognizer.process(image)
            .addOnSuccessListener {
                visionText = it.text
                latch.countDown()
            }
            .addOnFailureListener {
                latch.countDown()
            }
        latch.await()
        return visionText
    }

    @WorkerThread
    private fun loadModel() {
        if (mModule == null) {
            try {
                loadDictionaryFile(context.assets)
                Log.v(TAG, "Dictionary loaded.")
            } catch (ex: IOException) {
                Log.e(TAG, ex.message!!)
            }

            try {
                loadIdToLabelFile(context.assets)
                Log.v(TAG, "Id to label loaded.")
            } catch (ex: IOException) {
                Log.e(TAG, ex.message!!)
            }

            val moduleFileAbsoluteFilePath: String? = assetFilePath(context.assets)
            mModule = Module.load(moduleFileAbsoluteFilePath) // , mutableMapOf(), Device.CPU)
            Log.v(TAG, "Model loaded.")
        }
    }

    @WorkerThread
    fun runModel(visionText: String): MutableList<Pair<String, String>> {
        val sentenceTokenized: MutableList<Pair<String, String>> = mutableListOf()

        if (mModule != null) {
            featureConverter = FeatureConverter(
                dic,
                DO_LOWER_CASE,
                MAX_QUERY_LEN,
                MAX_SEQ_LEN
            )

            val feature: Feature = featureConverter.convert(visionText)
            val inputIds = feature.inputIds
            val inputMask = feature.inputMask
            val segmentIds = feature.segmentIds
            val startLogits = FloatArray(MAX_SEQ_LEN)
            val endLogits = FloatArray(MAX_SEQ_LEN)

            val output: MutableMap<Int, Any> = HashMap()
            output[0] = startLogits
            output[1] = endLogits

            val inputIdsTensor = Tensor.fromBlob(
                inputIds,
                longArrayOf(1, MAX_SEQ_LEN.toLong())
            )
            val inputMaskTensor = Tensor.fromBlob(
                inputMask,
                longArrayOf(1, MAX_SEQ_LEN.toLong())
            )
            val inputPredictions = IValue.from(
                inputIdsTensor
            )
            val inputMaskPredictions = IValue.from(
                inputMaskTensor
            )

            val labelIds = FeatureConverter.align_word_ids(feature)

            val outputTensor = mModule!!.forward(
                inputPredictions,
                inputMaskPredictions
            ).toTuple()

            val startLogitsTensor = outputTensor[0].toTensor()

            val startLogitsArray = startLogitsTensor.dataAsFloatArray

            val startLogitsList = List(startLogitsTensor.shape()[1].toInt()) { row ->
                List(startLogitsTensor.shape()[2].toInt()) { col ->
                    startLogitsArray[row * startLogitsTensor.shape()[2].toInt() + col]
                }
            }

            val startCleanLogitsList = startLogitsList.filterIndexed { index, _ ->
                labelIds[index] != -100
            }

            val startPredictionsList = startCleanLogitsList.map { row ->
                row.indexOf(row.maxOrNull())
            }

            var predictionsLabelList: List<String> = startPredictionsList.map { index ->
                labels[index]!!
            }

            for (i in predictionsLabelList.indices) {
                if (predictionsLabelList[i] != "O") {
                    Log.d(
                        "AIModel",
                        "Prediction: ${feature.origTokens[i]} -> ${predictionsLabelList[i]}"
                    )
                    sentenceTokenized.add(Pair(feature.origTokens[i], predictionsLabelList[i]))
                }
            }
        }

        return sentenceTokenized
    }

    private fun assetFilePath(assetManager: AssetManager): String? {
        assetManager.open(MODEL_PATH).use { ins ->
            val file = File(context.cacheDir, MODEL_PATH)
            FileOutputStream(file).use { out ->
                val buffer = ByteArray(1024)
                var read: Int
                while (ins.read(buffer).also { read = it } != -1) {
                    out.write(buffer, 0, read)
                }
                out.flush()
            }
            return file.absolutePath
        }
    }

    private fun loadDictionaryFile(assetManager: AssetManager) {
        assetManager.open(DIC_PATH).use { ins ->
            BufferedReader(InputStreamReader(ins)).use { reader ->
                var index = 0
                while (reader.ready()) {
                    val key = reader.readLine()
                    dic.put(key, index++)
                }
            }
        }
    }

    private fun loadIdToLabelFile(assetManager: AssetManager) {
        assetManager.open(LABEL_PATH).use { ins ->
            BufferedReader(InputStreamReader(ins)).use { reader ->
                var index = 0
                while (reader.ready()) {
                    val key = reader.readLine()
                    labels.put(index++, key)
                }
            }
        }
    }

    companion object {
        private var INSTANCE: PrescriptionAI? = null

        fun getInstance(context: Context): PrescriptionAI {
            return INSTANCE ?: synchronized(this) {
                val instance = PrescriptionAI(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
