package fr.medicapp.medicapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import fr.medicapp.medicapp.ai.PrescriptionAI
import fr.medicapp.medicapp.api.address.apiInteractions.MedicationDownload
import fr.medicapp.medicapp.mozilla.GeckoManager
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

/**
 * Activité principale de l'application.
 * Elle initialise la base de données et l'IA de prescription, et définit le contenu de l'activité.
 */
class MainActivity : ComponentActivity() {

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

        val isDownloaded = this.getSharedPreferences("medicapp", Context.MODE_PRIVATE)
            .getBoolean("isDataDownloaded", false)

        // Définition du contenu de l'activité
        setContent {
            var theme by remember { mutableStateOf(EUYellowColorShema) }
            // Utilisation du thème de l'application
            MedicAppTheme(
                theme = theme
            ) {

                //Téléchargement des médicaments
                MedicationDownload(this)

                // Initialisation de l'IA de prescription
                PrescriptionAI.getInstance(this)

                // Initialisation du moteur de rendu Gecko
                GeckoManager.getInstances(this)

                // Création du graphe de navigation racine
                RootNavGraph(
                    navController = rememberNavController(),
                    theme = theme,
                    isUser = isUser,
                    isDownload = isDownloaded,
                    context = this
                )
            }
        }
    }
}

