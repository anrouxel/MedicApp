package fr.medicapp.medicapp.ui.components.button

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.model.Alarm
import fr.medicapp.medicapp.ui.components.modal.DatePickerModal
import fr.medicapp.medicapp.ui.components.modal.TimePickerModal
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ReusableOutlinedTimePickerButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: Alarm?,
    label: String,
    onSelected: (Alarm) -> Unit
) {
    var open by remember { mutableStateOf(false) }
    var state = rememberTimePickerState()

    if (open) {
        TimePickerModal(
            state = state,
            onDismissRequest = { open = false },
            onConfirm = {
                open = false
                onSelected(
                    Alarm(
                        hour = state.hour,
                        minute = state.minute
                    )
                )
            }
        )
    }

    ReusableOutlinedTextFieldButton(
        modifier = modifier,
        value = value?.toString() ?: "",
        label = label,
        onClick = {
            open = true
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Light Theme", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ReusableOutlinedTimePickerButtonPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTimePickerButton(
            value = Alarm(
                hour = 12,
                minute = 30
            ),
            label = "Heure",
            onSelected = {}
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ReusableOutlinedTimePickerButtonDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTimePickerButton(
            value = Alarm(
                hour = 12,
                minute = 30
            ),
            label = "Heure",
            onSelected = {}
        )
    }
}
