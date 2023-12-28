package fr.medicapp.medicapp.ai

import android.content.res.AssetManager
import android.os.Handler
import android.os.HandlerThread
import androidx.annotation.WorkerThread
import fr.medicapp.medicapp.ai.tokenization.FeatureConverter
import org.pytorch.Module
import java.io.BufferedReader
import java.io.InputStreamReader

class PrescriptionAI {
    private lateinit var mModule: Module
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

    private val modelRunnable = Runnable {
        loadModel()
    }

    @WorkerThread
    private fun loadModel() {
    }

    /*private fun Context.loadModelFile(assetManager: AssetManager) {
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
            mModule = Module.load(file.absolutePath)
        }
    }*/

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
}