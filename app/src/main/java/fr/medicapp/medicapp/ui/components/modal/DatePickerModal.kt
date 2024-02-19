package fr.medicapp.medicapp.ui.components.modal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

/**
 * Cette fonction affiche un modal de sélection de date.
 *
 * @param state L'état du DatePicker, contenant la date sélectionnée.
 * @param onDismissRequest La fonction à exécuter lorsque l'utilisateur ferme le modal.
 * @param onConfirm La fonction à exécuter lorsque l'utilisateur confirme la date sélectionnée.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    state: DatePickerState,
    onDismissRequest: () -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                enabled = state.selectedDateMillis!=null
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
        DatePicker(
            state = state,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun DatePickerModalPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        DatePickerModal(
            state = rememberDatePickerState(),
            onDismissRequest = {},
            onConfirm = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun DatePickerModalDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        DatePickerModal(
            state = rememberDatePickerState(),
            onDismissRequest = {},
            onConfirm = {}
        )
    }
}
