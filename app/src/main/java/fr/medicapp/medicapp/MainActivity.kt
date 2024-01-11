package fr.medicapp.medicapp

import android.content.Context
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import fr.medicapp.medicapp.database.AppDatabase
import fr.medicapp.medicapp.ui.navigation.RootNavGraph
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getInstance(this)

        setContent {
            MedicAppTheme {
                val data by remember {
                    mutableStateOf(
                        this.getSharedPreferences(
                            "data",
                            Context.MODE_PRIVATE
                        ).getBoolean("getData", false)
                    )
                }

                if (!data) {
                    RootNavGraph(navController = rememberNavController())
                } else {
                    getData(this.assets)
                }
            }
        }
    }

    fun getData(assetManager: AssetManager) {
    }
}
