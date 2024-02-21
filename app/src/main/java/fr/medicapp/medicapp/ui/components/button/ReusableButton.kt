package fr.medicapp.medicapp.ui.components.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun ReusableButton(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        enabled = enabled,
        content = {
            ButtonContent(
                text = text,
                icon = icon
            )
        }
    )
}

@Preview(name = "Light Theme")
@Composable
private fun ReusableButtonPreview() {
    MedicAppTheme(
        darkTheme = false,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableButton(
            text = "Add",
            onClick = {}
        )
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun ReusableButtonDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableButton(
            text = "Add",
            onClick = {}
        )
    }
}
