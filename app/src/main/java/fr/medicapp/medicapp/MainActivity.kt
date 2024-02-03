package fr.medicapp.medicapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import fr.medicapp.medicapp.ai.PrescriptionAI
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialisation de l'IA de prescription
        PrescriptionAI.getInstance(this)

        // Définition du contenu de l'activité
        setContent {
            // Utilisation du thème de l'application
            MedicAppTheme {
                // Création du graphe de navigation racine
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}
