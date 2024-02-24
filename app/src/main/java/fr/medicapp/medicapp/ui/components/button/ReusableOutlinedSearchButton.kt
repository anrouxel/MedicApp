package fr.medicapp.medicapp.ui.components.button

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.model.prescription.Alarm
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.ui.components.modal.SearchModal
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReusableOutlinedSearchButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    options: List<OptionDialog>,
    value: Any?,
    label: String,
    warnings: Boolean = false,
    onSelected: (OptionDialog) -> Unit
) {
    var open by remember { mutableStateOf(false) }

    if (open) {
        SearchModal(
            title = "Recherche",
            options = options,
            onDismissRequest = {
                open = false
            },
            onConfirm = {
                onSelected(it)
                open = false
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
private fun ReusableOutlinedSearchButtonPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedSearchButton(
            options = listOf(),
            value = Alarm(
                time = LocalTime.now()
            ),
            label = "Recherche",
            onSelected = {}
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ReusableOutlinedSearchButtonDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedSearchButton(
            options = listOf(),
            value = Alarm(
                time = LocalTime.now()
            ),
            label = "Recherche",
            onSelected = {}
        )
    }
}
