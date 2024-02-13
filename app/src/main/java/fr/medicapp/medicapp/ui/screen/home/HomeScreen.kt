package fr.medicapp.medicapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.components.calendar.CalendarButton
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen120
import io.github.boguszpawlowski.composecalendar.rememberSelectableWeekCalendarState
import io.github.boguszpawlowski.composecalendar.selection.SelectionMode
import java.util.Date

/**
 * Écran d'accueil de l'application MedicApp.
 *
 * Cet écran affiche un message de bienvenue à l'utilisateur et propose plusieurs actions :
 * - Ajouter une ordonnance
 * - Signaler un effet indésirable
 * - Ajouter un rappel
 *
 * @param onAddPrescriptionClick Fonction à appeler lorsque l'utilisateur clique sur le bouton "Ajouter une ordonnance".
 * @param onAddSideEffectClick Fonction à appeler lorsque l'utilisateur clique sur le bouton "Signaler un effet indésirable".
 * @param onAddNotification Fonction à appeler lorsque l'utilisateur clique sur le bouton "Ajouter un rappel".
 */
@Composable
fun HomeScreen(
    onAddPrescriptionClick: () -> Unit,
) {
    val date = rememberSelectableWeekCalendarState(initialSelectionMode = SelectionMode.Single)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CalendarButton(
            date = date,
            onClick = { })
        Button(
            onClick = onAddPrescriptionClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUGreen100
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Ajouter une ordonnance")
        }
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUGreen100
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Signaler un effet indésirable")
        }
    }
}

/**
 * Prévisualisation de l'écran d'accueil.
 *
 * Cette prévisualisation permet de voir à quoi ressemble l'écran d'accueil sans avoir à lancer l'application.
 */
@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onAddPrescriptionClick = { }
    )
}
