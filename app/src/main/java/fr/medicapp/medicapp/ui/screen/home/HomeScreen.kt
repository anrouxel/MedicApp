package fr.medicapp.medicapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import fr.medicapp.medicapp.ui.components.calendar.Calendar
import fr.medicapp.medicapp.ui.components.list.ListOfMedication
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.LocalDate

/**
 * Écran d'accueil de l'application MedicApp.
 *
 * Cet écran affiche un message de bienvenue à l'utilisateur et propose plusieurs actions :
 * - Ajouter une ordonnance
 * - Signaler un effet indésirable
 * - Ajouter un rappel
 *
 * @param onAddPrescriptionClick Fonction à appeler lorsque l'utilisateur clique sur le bouton "Ajouter une ordonnance".
 */
@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        ) {

        val context = LocalContext.current
        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(PrescriptionEntity::class.java)

        val prescriptions = store.all.map { it.convert() }

        val selection = remember { mutableStateOf(LocalDate.now()) }
        Calendar(selection = selection)
        Spacer(modifier = Modifier.height(8.dp))
        ListOfMedication(selectedDate = selection.value, prescription = prescriptions)
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
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        HomeScreen()
    }
}

@Preview
@Composable
fun PreviewListOfMedication() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        ListOfMedication(selectedDate = LocalDate.now(), prescription = listOf())
    }
}