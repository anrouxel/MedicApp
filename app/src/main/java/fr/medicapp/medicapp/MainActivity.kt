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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    private val MAX_ANS_LEN = 32
    private val MAX_QUERY_LEN = 64
    private val MAX_SEQ_LEN = 512
    private val DO_LOWER_CASE = false
    private val PREDICT_ANS_NUM = 5
    private val NUM_LITE_THREADS = 4

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                                    Log.d("MLKit", visionText.text)
                                    this.visionText = visionText.text
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

                                    Log.v(TAG, "Convert Feature...")
                                    val feature: Feature = featureConverter.convert(visionText.text)
                                    Log.v(TAG, "Set inputs...")
                                    val inputIds = feature.inputIds
                                    val inputMask = feature.inputMask
                                    val segmentIds = feature.segmentIds
                                    val startLogits = FloatArray(MAX_SEQ_LEN)
                                    val endLogits = FloatArray(MAX_SEQ_LEN)

                                    // Show token and tokenoToOrigMap
                                    feature.origTokens.forEachIndexed { index, s ->
                                        Log.v(TAG, "origTokens[$index] = $s")
                                    }

                                    /*inputIds.forEachIndexed { index, i ->
                                        Log.v(TAG, "inputIds[$index] = $i")
                                    }*/

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

                                    Log.v(TAG, "inputIdsTensor: $inputIdsTensor")
                                    Log.v(TAG, "inputIdsTensor.size: ${inputIdsTensor.shape()}")
                                    Log.v(TAG, "inputIdsTensor.dataAsFloatArray: ${inputIdsTensor.dataAsIntArray.toList()}")
                                    Log.v(TAG, "inputMaskTensor: $inputMaskTensor")
                                    Log.v(TAG, "inputMaskTensor.size: ${inputMaskTensor.shape()}")
                                    Log.v(TAG, "inputMaskTensor.dataAsFloatArray: ${inputMaskTensor.dataAsIntArray.toList()}")

                                    val inputPredictions = IValue.from(
                                        inputIdsTensor
                                    )
                                    val inputMaskPredictions = IValue.from(
                                        inputMaskTensor
                                    )

                                    val labelIds = FeatureConverter.align_word_ids(feature)

                                    /*Log.v(TAG, "Model loading...")
                                    while (mModule == null) {
                                        Log.v(TAG, "Model not loaded yet...")
                                    }
                                    Log.v(TAG, "Model loaded.")*/

                                    Log.v(TAG, "Predicting...")

                                    val outputTensor = mModule!!.forward(
                                        inputPredictions,
                                        inputMaskPredictions
                                    ).toTuple()

                                    /*
                                        logits = model(input_id, mask)
                                logits_clean = logits[0][label_ids != -100]

                                predictions = logits_clean.argmax(dim=1).tolist()
                                prediction_label = [ids_to_labels[i] for i in predictions]
                                print(sentence)
                                print(prediction_label)
                                     */

                                    Log.v(TAG, "Predictions done.")

                                    Log.v(TAG, "inputIdsTensor: ${inputIds.size}")
                                    Log.v(TAG, "inputMaskTensor: ${inputMask.size}")
                                    Log.v(TAG, "labelIds: ${labelIds.size}")

                                    val startLogitsTensor = outputTensor[0].toTensor() // Tensor([1, 20, 3], dtype=torch.float32)
                                    Log.v(TAG, "startLogitsTensor: $startLogitsTensor")
                                    Log.v(TAG, "startLogitsTensor.size: ${startLogitsTensor.shape()}")
                                    val startLogitsArray = startLogitsTensor.dataAsFloatArray
                                    Log.v(TAG, "startLogitsArray: $startLogitsArray")
                                    Log.v(TAG, "startLogitsArray.size: ${startLogitsArray.size}")
                                    val startLogitsList = List(startLogitsTensor.shape()[1].toInt()) { row ->
                                        List(startLogitsTensor.shape()[2].toInt()) { col ->
                                            startLogitsArray[row * startLogitsTensor.shape()[2].toInt() + col]
                                        }
                                    }
                                    Log.v(TAG, "startLogitsList: $startLogitsList")
                                    Log.v(TAG, "startLogitsList.size: ${startLogitsList.size}")
                                    val startCleanLogitsList = startLogitsList.filterIndexed { index, _ ->
                                        labelIds[index] != -100
                                    }
                                    Log.v(TAG, "startCleanLogitsList: $startCleanLogitsList")
                                    Log.v(TAG, "startCleanLogitsList.size: ${startCleanLogitsList.size}")
                                    val startPredictionsList = startCleanLogitsList.map { row ->
                                        row.indexOf(row.maxOrNull())
                                    }
                                    Log.v(TAG, "startPredictionsList: $startPredictionsList")
                                    Log.v(TAG, "startPredictionsList.size: ${startPredictionsList.size}")

                                    var predictionsLabelList: List<String> = startPredictionsList.map { index ->
                                        labels[index]!!
                                    }
                                    Log.v(TAG, "predictionsLabelList: $predictionsLabelList")

                                    Log.v(TAG, "visionText: ${visionText.text.split(" ").size}")
                                    Log.v(TAG, "predictionsLabelList: ${predictionsLabelList.size}")
                                    Log.v(TAG, "origTokens: ${feature.origTokens.size}")

                                    for (i in predictionsLabelList.indices) {
                                        Log.v(TAG, predictionsLabelList[i])
                                        if (predictionsLabelList[i] != "O") {
                                            Log.v(TAG, "origTokens[$i] = ${feature.origTokens[i]}")
                                            Log.v(TAG, "predictionsLabelList[$i] = ${predictionsLabelList[i]}")
                                            sentenceTokenized.add(
                                                Pair(
                                                    feature.origTokens[i],
                                                    predictionsLabelList[i]
                                                )
                                            )
                                        }
                                    }
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
                            Text("Image prise")

                            /*Text(text = visionText)

                            sentenceTokenized.forEach {
                                Text("${it.first} : ${it.second}")
                            }*/
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

        mBackgroundThread = HandlerThread("BackgroundThread")
        mBackgroundThread.start()
        mHandle = Handler(mBackgroundThread.looper)

        mHandle.removeCallbacks(modelRunnable)
        mHandle.post(modelRunnable)
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
            mModule = Module.load(moduleFileAbsoluteFilePath, mutableMapOf(), Device.CPU)
            Log.v(TAG, "Model loaded.")
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
