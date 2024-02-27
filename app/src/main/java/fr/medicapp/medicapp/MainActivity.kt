package fr.medicapp.medicapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import fr.medicapp.medicapp.ai.PrescriptionAI
import fr.medicapp.medicapp.api.address.APIAddressClient
import fr.medicapp.medicapp.api.address.medication.MedicationDownload
import fr.medicapp.medicapp.database.converter.LocalDateTypeAdapter
import fr.medicapp.medicapp.database.repositories.medication.MedicationRepository
import fr.medicapp.medicapp.model.gson.MedicationGSON
import fr.medicapp.medicapp.mozilla.GeckoManager
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.lang.reflect.Type
import java.time.LocalDate

/**
 * Activité principale de l'application.
 * Elle initialise la base de données et l'IA de prescription, et définit le contenu de l'activité.
 */
class MainActivity : ComponentActivity() {

    /**
     * Indique si les données ont été téléchargées.
     */
    private var isDataDownloaded = false


    /**
     * Crée l'activité. Cette méthode est appelée lorsque l'activité est créée.
     *
     * @param savedInstanceState Si l'activité est recréée après avoir été tuée par le système, c'est le bundle qui contient l'état de l'activité. Sinon, c'est null.
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isUser = this.getSharedPreferences("medicapp", Context.MODE_PRIVATE)
            .getBoolean("isUserCreated", false)

        Thread {

        }.start()

        //Téléchargement des médicaments
        MedicationDownload(this)

        // Initialisation de l'IA de prescription
        PrescriptionAI.getInstance(this)

        // Initialisation du moteur de rendu Gecko
        GeckoManager.getInstances(this)

        // Définition du contenu de l'activité
        setContent {
            var theme by remember { mutableStateOf(EUYellowColorShema) }
            // Utilisation du thème de l'application
            MedicAppTheme(
                theme = theme
            ) {
                if (isDataDownloaded) {
                    // Création du graphe de navigation racine
                    RootNavGraph(
                        navController = rememberNavController(),
                        theme = theme,
                        onThemeChange = { theme = it },
                        isUser = isUser
                    )
                } else {
                    // Affichage de l'écran de chargement
                    LoadingScreen(this)
                }
            }
        }
    }

    @Composable
    fun LoadingScreen(context: Context) {
        // Appel à LaunchedEffect pour démarrer le téléchargement
        LaunchedEffect(Unit) {
            MedicationDownload(context)
            // Une fois le téléchargement terminé, mettre à jour l'état
            isDataDownloaded = true
        }
    }
}
