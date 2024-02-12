package fr.medicapp.medicapp.ui.components.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun ReusableOutlinedTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    enabled: Boolean = true,
    warnings: Boolean = false,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    OutlinedTextField(
        modifier = modifier,
        enabled = enabled,
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label
            )
        },
        shape = MaterialTheme.shapes.medium,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            disabledBorderColor = MaterialTheme.colorScheme.primary,
            errorBorderColor = MaterialTheme.colorScheme.error,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            disabledLabelColor = MaterialTheme.colorScheme.primary,
            errorLabelColor = MaterialTheme.colorScheme.error,
            focusedTextColor = MaterialTheme.colorScheme.onSurface,
            unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
            errorTextColor = MaterialTheme.colorScheme.error
        ),
        textStyle = MaterialTheme.typography.bodyMedium,
        trailingIcon = {
            if (warnings) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Warning",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}

@Preview(name = "Light Theme", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ReusableOutlinedTextFieldPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextField(
            value = "Hello",
            onValueChange = {},
            label = ""
        )
    }
}

@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ReusableOutlinedTextFieldDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextField(
            value = "Hello",
            onValueChange = {},
            label = ""
        )
    }
}

@Preview(name = "Light Theme", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ReusableOutlinedTextFieldLabelPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextField(
            value = "Hello",
            onValueChange = {},
            label = "World"
        )
    }
}

@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ReusableOutlinedTextFieldLabelDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextField(
            value = "Hello",
            onValueChange = {},
            label = "World"
        )
    }
}

@Preview(name = "Light Theme", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ReusableOutlinedTextFieldWarningPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextField(
            value = "Hello",
            onValueChange = {},
            label = "World",
            warnings = true
        )
    }
}

@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ReusableOutlinedTextFieldWarningDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextField(
            value = "Hello",
            onValueChange = {},
            label = "World",
            warnings = true
        )
    }
}
