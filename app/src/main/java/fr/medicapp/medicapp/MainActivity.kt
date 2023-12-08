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
import fr.medicapp.medicapp.tokenization.FullTokenizer
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.Tensor
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
        val feature: Feature = featureConverter.convert("OphtAlliance Docteur Frank BECQUET CHIRURGIE OCULAIRE2-4 route de Paris 44300 NANTES N\\u00B0 44 1 70362 6 RPPS 10000601426 Secteur 2 SELARL OPHTALLIANCE CONSULTATIONS 02:28 44 33 73 secretaire.becquet@ophtalliance.fr CHIRURGIE-Clinique Jules Verne 2-4 route de Paris 44300 NANTES Nantes, le 17/08/2023 Monsieur ROUXEL Antonin ORDONNANCE DE TRAITEMENT Oeil droit CAPTOPRIL SANDOZ 25 MG, COMPRIM\\u00C9 QUADRIS\\u00C9CABLE I goutte le soir tous les jours pendant 1 mois TRAITEMENT A RENOUVELER PENDANT \\u0026 MOIS 234567 SextmyAMORAY CRO-IM 3000-N WHORENTOERE COMING DADRIDIC LOUNGMARCHA HENRY-HERMOUR-G JACK KARRI LANKA SOCORRO WARS \\u0026 MONKU - THAT LOREM PETRESCUE SALOON RESANTING-ETHOMA En cas d\\u0027urgence de mult ou le week end valler appeler se 13 Prenez rendes vous en quelques clics sur www.doctolb.fs ou www.ophtalliance.fr")
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

        Log.v(TAG, "Model loading...")
        while (mModule == null) {
            Log.v(TAG, "Model not loaded yet...")
        }
        Log.v(TAG, "Model loaded.")

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
            Log.v(TAG, "index: $index, labelIds[index]: ${labelIds[index]}")
            labelIds[index] != -100
        }
        Log.v(TAG, "startCleanLogitsList: $startCleanLogitsList")
        Log.v(TAG, "startCleanLogitsList.size: ${startCleanLogitsList.size}")
        val startPredictionsList = startCleanLogitsList.map { row ->
            row.indexOf(row.maxOrNull())
        }
        Log.v(TAG, "startPredictionsList: $startPredictionsList")
        Log.v(TAG, "startPredictionsList.size: ${startPredictionsList.size}")

        val predictionsLabelList = startPredictionsList.map { index ->
            labels[index]
        }
        Log.v(TAG, "predictionsLabelList: $predictionsLabelList")
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
}
