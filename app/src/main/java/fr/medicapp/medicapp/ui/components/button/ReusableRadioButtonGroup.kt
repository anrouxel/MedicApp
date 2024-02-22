package fr.medicapp.medicapp.ui.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun ReusableRadioGroup(
    modifier: Modifier = Modifier.fillMaxWidth(),
    options: List<String>,
    selectedOption: String,
    label: String,
    onClick: (String) -> Unit,
) {
    Column {
        Text(
            text = label,
            modifier = modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colorScheme.primary
        )
        options.forEach { text ->
            Row(
                modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onClick(text) }
                )
                Text(
                    text = text,
                    modifier = modifier.padding(start = 8.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview
@Composable
fun ReusableRadioGroupPreview() {
    val selectedOption = "Option 1"

    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        ReusableRadioGroup(
            options = listOf("Option 1", "Option 2", "Option 3"),
            selectedOption = selectedOption,
            label = "Youpi",
            onClick = {}
        )
    }

}

@Preview
@Composable
fun ReusableRadioGroupDarkPreview() {
    val selectedOption = "Option 1"

    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        ReusableRadioGroup(
            options = listOf("Option 1", "Option 2", "Option 3"),
            selectedOption = selectedOption,
            label = "Youpi",
            onClick = {}
        )
    }

}