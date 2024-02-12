package fr.medicapp.medicapp.ui.components.button

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReusableOutlinedTextFieldButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    label: String,
    warnings: Boolean = false,
    onClick: () -> Unit,
    onLongClick: () -> Unit = {}
) {
    OutlinedTextField(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        enabled = false,
        value = value,
        onValueChange = {},
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
            errorTextColor = MaterialTheme.colorScheme.error,
            disabledTrailingIconColor = MaterialTheme.colorScheme.error
        ),
        textStyle = MaterialTheme.typography.bodyMedium,
        trailingIcon = {
            if (warnings) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Warning",
                )
            }
        }
    )
}

@Preview(name = "Light Theme", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ReusableOutlinedTextFieldButtonPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextFieldButton(
            value = "Hello",
            label = "",
            onClick = {}
        )
    }
}

@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ReusableOutlinedTextFieldButtonDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextFieldButton(
            value = "Hello",
            label = "",
            onClick = {}
        )
    }
}

@Preview(name = "Light Theme", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ReusableOutlinedTextFieldButtonLabelPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextFieldButton(
            value = "Hello",
            label = "World",
            onClick = {}
        )
    }
}

@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ReusableOutlinedTextFieldButtonLabelDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextFieldButton(
            value = "Hello",
            label = "World",
            onClick = {}
        )
    }
}

@Preview(name = "Light Theme", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ReusableOutlinedTextFieldButtonWarningPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextFieldButton(
            value = "Hello",
            label = "World",
            warnings = true,
            onClick = {}
        )
    }
}

@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ReusableOutlinedTextFieldButtonWarningDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableOutlinedTextFieldButton(
            value = "Hello",
            label = "World",
            warnings = true,
            onClick = {}
        )
    }
}
