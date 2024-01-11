package fr.medicapp.medicapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import fr.medicapp.medicapp.database.AppDatabase
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppDatabase.getInstance(this)

        setContent {
            MedicAppTheme {
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}
