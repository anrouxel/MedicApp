package fr.medicapp.medicapp.ui.components.button

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.model.Duration
import fr.medicapp.medicapp.ui.components.modal.DateRangePickerModal
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ReusableOutlinedDateRangePickerButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: Duration?,
    label: String,
    warnings: Boolean = false,
    onSelected: (Duration) -> Unit
) {
    var open by remember { mutableStateOf(false) }
    var state = rememberDateRangePickerState()

    if (open) {
        DateRangePickerModal(
            state = state,
            onDismissRequest = { open = false },
            onConfirm = {
                open = false
                val localDateStart = Instant.ofEpochMilli(
                    state.selectedStartDateMillis!!
                ).atZone(ZoneId.systemDefault()).toLocalDate()
                val localDateEnd = Instant.ofEpochMilli(
                    state.selectedEndDateMillis!!
                ).atZone(ZoneId.systemDefault()).toLocalDate()
                onSelected(
                    Duration(
                        startDate = localDateStart,
                        endDate = localDateEnd
                    )
                )
            }
        )
    }

    ReusableOutlinedTextFieldButton(
        modifier = modifier,
        value = value?.toString() ?: "",
        label = label,
        warnings = warnings,
        onClick = {
            open = true
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Theme", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ReusableOutlinedDateRangePickerButtonPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedDateRangePickerButton(
            value = Duration(
                startDate = LocalDate.now(),
                endDate = LocalDate.now()
            ),
            label = "Durée",
            onSelected = {}
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ReusableOutlinedDateRangePickerButtonDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedDateRangePickerButton(
            value = Duration(
                startDate = LocalDate.now(),
                endDate = LocalDate.now()
            ),
            label = "Durée",
            onSelected = {}
        )
    }
}
