package fr.medicapp.medicapp

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.WorkerThread
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.navigation.compose.rememberNavController
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import org.pytorch.Module
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : ComponentActivity() {

    private var mModule: Module? = null
    private lateinit var mBackgroundThread: HandlerThread
    private lateinit var mHandle: Handler

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

        //mHandle.removeCallbacks(modelRunnable)
        //mHandle.post(modelRunnable)

        // PyTorchAndroid.setNumThreads(1)
        /*val module: Module = Module.load(
            assetFilePath(
                context = this,
                assetName = "model.pt"
            )
        )*/
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
}
