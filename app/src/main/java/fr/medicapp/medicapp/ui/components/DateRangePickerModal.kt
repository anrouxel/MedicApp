package fr.medicapp.medicapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

/**
 * Cette fonction affiche un modal de sélection de plage de dates.
 *
 * @param state L'état du DateRangePicker, contenant la plage de dates sélectionnée.
 * @param onDismissRequest La fonction à exécuter lorsque l'utilisateur ferme le modal.
 * @param onConfirm La fonction à exécuter lorsque l'utilisateur confirme la plage de dates sélectionnée.
 * @param colors Les couleurs du DateRangePicker.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    state: DateRangePickerState,
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
                Text("CANCEL")
            }
        }
    ) {
        DateRangePicker(
            state = state,
        )
    }
}