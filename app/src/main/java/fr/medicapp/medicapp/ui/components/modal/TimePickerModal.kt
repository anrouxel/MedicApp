package fr.medicapp.medicapp.ui.components.modal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

/**
 * Cette fonction affiche une boîte de dialogue de sélection de l'heure avec des options spécifiques.
 *
 * @param state L'état du sélecteur de temps.
 * @param clockBackgroundColor La couleur de fond de l'horloge.
 * @param selectorColor La couleur du marqueur pour changer l'heure.
 * @param timeSelectorSelectedContainerColor La couleur du fond de l'objet sélectionné.
 * @param timeSelectorUnselectedContainerColor La couleur du fond de l'objet non sélectionné.
 * @param onDismissRequest La fonction à exécuter lorsque l'utilisateur ferme la boîte de dialogue.
 * @param onConfirm La fonction à exécuter lorsque l'utilisateur confirme la sélection.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerModal(
    state: TimePickerState,
    onDismissRequest: () -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text("Annuler")
            }
        }
    ) {
        Spacer(modifier = Modifier.padding(10.dp))

        TimePicker(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            state = state
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun TimePickerModalPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        TimePickerModal(
            state = rememberTimePickerState(),
            onDismissRequest = {},
            onConfirm = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun TimePickerModalDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        TimePickerModal(
            state = rememberTimePickerState(),
            onDismissRequest = {},
            onConfirm = {}
        )
    }
}
