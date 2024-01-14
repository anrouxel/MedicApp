package fr.medicapp.medicapp.ui.messages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Composable représentant l'écran des messages de l'application MedicApp.
 *
 * Cet écran affiche les messages échangés entre l'utilisateur et un destinataire spécifique.
 *
 * @param message Les informations du message à afficher.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Messages(
    message: TestMessages
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                        message.destinataire,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
            ) {

            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
        ) {
            /*TODO*/
        }
    }
}

/**
 * Prévisualisation de l'écran des messages.
 *
 * Cette prévisualisation permet de voir à quoi ressemble l'écran des messages sans avoir à lancer l'application.
 */
@Preview(showBackground = true)
@Composable
private fun MessagesPreview() {
    val msg = TestMessages(
        "Dr. MOTTU",
        true,
        listOf("Bonjour", "Bonjour", "Au revoir")
    )
    Messages(msg)
}
