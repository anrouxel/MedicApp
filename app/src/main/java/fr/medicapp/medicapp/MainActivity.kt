package fr.medicapp.medicapp

import android.content.Context
import android.content.res.AssetManager
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.WorkerThread
import androidx.navigation.compose.rememberNavController
import fr.medicapp.medicapp.tokenization.Feature
import fr.medicapp.medicapp.tokenization.FeatureConverter
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import org.pytorch.Module
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader


class MainActivity : ComponentActivity() {

    private var mModule: Module? = null
    private lateinit var mBackgroundThread: HandlerThread
    private lateinit var mHandle: Handler

    private val TAG = "CamemBERT"
    private val DIC_PATH = "vocab.txt"
    private val dic: HashMap<String, Int> = HashMap()
    private lateinit var featureConverter: FeatureConverter
    private val MAX_ANS_LEN = 32
    private val MAX_QUERY_LEN = 64
    private val MAX_SEQ_LEN = 384
    private val DO_LOWER_CASE = true
    private val PREDICT_ANS_NUM = 5
    private val NUM_LITE_THREADS = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicAppTheme {
                RootNavGraph(navController = rememberNavController())
            }
        }

        mBackgroundThread = HandlerThread("BackgroundThread")
        mBackgroundThread.start()
        mHandle = Handler(mBackgroundThread.looper)

        mHandle.removeCallbacks(modelRunnable)
        mHandle.post(modelRunnable)

        // PyTorchAndroid.setNumThreads(1)
       /*val module: Module = Module.load(
            assetFilePath(
                context = this,
                assetName = "model2.pt"
            )
        )*/

        try {
            loadDictionaryFile(this.assets)
            Log.v(TAG, "Dictionary loaded.")
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
        val feature: Feature = featureConverter.convert("Quel est le nom du médicament ?", "Quel est le nom du médicament ?")
        Log.v(TAG, "Set inputs...")
        val inputIds = Array(1) {
            IntArray(
                MAX_SEQ_LEN
            )
        }
        val inputMask = Array(1) {
            IntArray(
                MAX_SEQ_LEN
            )
        }
        val segmentIds = Array(1) {
            IntArray(
                MAX_SEQ_LEN
            )
        }
        val startLogits = Array(1) { FloatArray(MAX_SEQ_LEN) }
        val endLogits = Array(1) {
            FloatArray(
                MAX_SEQ_LEN
            )
        }

        Log.v(TAG, "Convert Feature...")
        for (j in 0 until MAX_SEQ_LEN) {
            Log.v(TAG, "j = $j")
            inputIds[0][j] = feature.inputIds[j]
            Log.v(TAG, "inputIds[0][$j] = ${feature.inputIds[j]}")
            inputMask[0][j] = feature.inputMask[j];
            Log.v(TAG, "inputMask[0][$j] = ${feature.inputMask[j]}")
            segmentIds[0][j] = feature.segmentIds[j];
            Log.v(TAG, "segmentIds[0][$j] = ${feature.segmentIds[j]}")
        }
        val output: MutableMap<Int, Any> = HashMap()
        output[0] = startLogits
        output[1] = endLogits
    }

    private val modelRunnable = Runnable {
        loadModel()
    }

    @WorkerThread
    private fun loadModel() {
        if (mModule == null) {
            val moduleFileAbsoluteFilePath: String? = assetFilePath(this, "model.pt")
            mModule = Module.load(moduleFileAbsoluteFilePath)
        }
    }

    private fun assetFilePath(context: Context, assetName: String): String? {
        val file = File(context.filesDir, assetName)

        try {
            context.assets.open(assetName).use { `is` ->
                FileOutputStream(file).use { os ->
                    val buffer = ByteArray(4 * 1024)
                    while (true) {
                        val length = `is`.read(buffer)
                        if (length <= 0) {
                            break
                        }
                        os.write(buffer, 0, length)
                    }
                    os.flush()
                    os.close()
                }
                return file.absolutePath
            }
        } catch (e: IOException) {
            Log.e("pytorchandroid", "Error process asset $assetName to file path")
        }
        return null
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
}
