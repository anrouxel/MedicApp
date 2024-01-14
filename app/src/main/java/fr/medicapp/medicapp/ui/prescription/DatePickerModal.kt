package fr.medicapp.medicapp.ui.prescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

/**
 * Cette fonction affiche un modal de sélection de date.
 *
 * @param state L'état du DatePicker, contenant la date sélectionnée.
 * @param onDismissRequest La fonction à exécuter lorsque l'utilisateur ferme le modal.
 * @param onConfirm La fonction à exécuter lorsque l'utilisateur confirme la date sélectionnée.
 * @param colors Les couleurs du DatePicker.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    state: DatePickerState,
    onDismissRequest: () -> Unit = {},
    onConfirm: () -> Unit = {},
    colors: DatePickerColors = DatePickerDefaults.colors(),
) {
    DatePickerDialog(
        colors = colors,
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
    DatePickerModal(
        DatePickerState(1704898282476, 1704898282476, IntRange(2000,2010), DisplayMode.Picker)
    )
}