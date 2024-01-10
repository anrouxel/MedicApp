package fr.medicapp.medicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import fr.medicapp.medicapp.database.AppDatabase
import fr.medicapp.medicapp.entity.DoctorEntity
import fr.medicapp.medicapp.entity.PrescriptionEntity
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MedicAppTheme {
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}
