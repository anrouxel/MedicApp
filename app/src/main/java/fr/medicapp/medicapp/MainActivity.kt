package fr.medicapp.medicapp

import android.content.Context
import android.content.res.AssetManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.WorkerThread
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import fr.medicapp.medicapp.tokenization.Feature
import fr.medicapp.medicapp.tokenization.FeatureConverter
import fr.medicapp.medicapp.tokenization.FullTokenizer
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import org.pytorch.Device
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.Tensor
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {

    private var mModule by mutableStateOf<Module?>(null)
    private var result by mutableStateOf<Boolean>(false)
    private var sentenceTokenized by mutableStateOf<MutableList<Pair<String, String>>>(mutableListOf())
    private var visionText by mutableStateOf<String>("")
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

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBackgroundThread = HandlerThread("BackgroundThread")
        mBackgroundThread.start()
        mHandle = Handler(mBackgroundThread.looper)

        mHandle.removeCallbacks(modelRunnable)
        mHandle.post(modelRunnable)

        setContent {
            MedicAppTheme {

                val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

                val cameraPermissionState = rememberPermissionState(
                    android.Manifest.permission.CAMERA
                )

                var hasImage by remember { mutableStateOf(false) }

                var imageUri by remember { mutableStateOf<Uri?>(null) }

                val cameraLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.TakePicture(),
                    onResult = { success: Boolean ->
                        hasImage = success

                        if (imageUri != null) {
                            val image = InputImage.fromFilePath(this, imageUri!!)
                            recognizer.process(image)
                                .addOnSuccessListener { visionText ->
                                    this.visionText = visionText.text
                                    mHandle.removeCallbacks(textRunnable)
                                    mHandle.post(textRunnable)
                                }
                                .addOnFailureListener { e ->
                                    Log.d("MLKit", e.toString())
                                }
                        }
                    }
                )
                //RootNavGraph(navController = rememberNavController())
                // si loaded = false alors on affiche un ring progress
                // sinon on affiche le RootNavGraph
                if (mModule != null) {
                    if (cameraPermissionState.status.isGranted) {
                        if (hasImage) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp)
                                    .verticalScroll(
                                        enabled = true,
                                        state = rememberScrollState()
                                    )
                            ) {
                                Text("Image prise")

                                if (result) {
                                    Button(onClick = {
                                        hasImage = false
                                        sentenceTokenized = mutableListOf()
                                        result = false
                                    }) {
                                        Text("Recommencer")
                                    }

                                    Text("Résultat")

                                    Text(visionText)

                                    Text("sentenceTokenized size: ${sentenceTokenized.size}")


                                    Column {
                                        sentenceTokenized.forEach { (token, label) ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                            ) {
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .weight(1f)
                                                ) {
                                                    Text(text = token)
                                                }
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .weight(1f)
                                                ) {
                                                    Text(text = label)
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            //Text(text = visionText)
                        } else {
                            Button(onClick = {
                                val uri: Uri = this.createImageFile()
                                imageUri = uri
                                cameraLauncher.launch(uri)
                            }) {
                                Text("Prendre une photo")
                            }
                        }
                    } else {
                        Button(onClick = {
                            cameraPermissionState.launchPermissionRequest()
                        }) {
                            Text("Autoriser l'accès à la caméra")
                        }
                    }
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize(),
                    )
                }
            }
        }
    }

    /*override fun onStart() {
        super.onStart()

        // PyTorchAndroid.setNumThreads(1)
       /*val module: Module = Module.load(
            assetFilePath(
                context = this,
                assetName = "model2.pt"
            )
        )*/
    }*/

    private val modelRunnable = Runnable {
        loadModel()
    }

    @WorkerThread
    private fun loadModel() {
        if (mModule == null) {
            val moduleFileAbsoluteFilePath: String? = assetFilePath(this.assets)
            mModule = Module.load(moduleFileAbsoluteFilePath)//, mutableMapOf(), Device.CPU)
            Log.v(TAG, "Model loaded.")
        }
    }

    private val textRunnable = Runnable {
        runModel()
    }

    @WorkerThread
    private fun runModel() {
        if (mModule != null) {
            try {
                loadDictionaryFile(this.assets)
                Log.v(TAG, "Dictionary loaded.")
            } catch (ex: IOException) {
                Log.e(TAG, ex.message!!)
            }

            try {
                loadIdToLabelFile(this.assets)
                Log.v(TAG, "Id to label loaded.")
            } catch (ex: IOException) {
                Log.e(TAG, ex.message!!)
            }

            featureConverter = FeatureConverter(
                dic,
                DO_LOWER_CASE,
                MAX_QUERY_LEN,
                MAX_SEQ_LEN
            )

            val feature: Feature = featureConverter.convert(this.visionText)
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
                    sentenceTokenized.add(Pair(feature.origTokens[i], predictionsLabelList[i]))
                }
            }

            result = true
        }
    }

    private fun assetFilePath(assetManager: AssetManager): String? {
        assetManager.open(MODEL_PATH).use { ins ->
            val file = File(cacheDir, MODEL_PATH)
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

    fun loadDictionaryFile(assetManager: AssetManager) {
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

    fun loadIdToLabelFile(assetManager: AssetManager) {
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

    fun Context.createImageFile(): Uri {
        val provider: String = "${applicationContext.packageName}.fileprovider"
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"

        val image = File.createTempFile(
            imageFileName,
            ".jpg",
            cacheDir
        ).apply {
            createNewFile()
        }

        return FileProvider.getUriForFile(applicationContext, provider, image)
    }
}
