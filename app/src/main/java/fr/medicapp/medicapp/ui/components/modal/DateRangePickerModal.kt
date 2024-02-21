package fr.medicapp.medicapp.ui.components.modal

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

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
) {
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                enabled = state.selectedStartDateMillis != null && state.selectedEndDateMillis != null
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
        Spacer(modifier = Modifier.padding(10.dp))

        DateRangePicker(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            state = state,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun DateRangePickerModalPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        DateRangePickerModal(
            state = rememberDateRangePickerState(),
            onDismissRequest = {},
            onConfirm = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun DateRangePickerModalDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        DateRangePickerModal(
            state = rememberDateRangePickerState(),
            onDismissRequest = {},
            onConfirm = {}
        )
    }
}
