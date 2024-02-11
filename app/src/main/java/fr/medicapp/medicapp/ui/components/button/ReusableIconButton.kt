package fr.medicapp.medicapp.ui.components.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun ReusableIconButton(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    ButtonCard(
        onClick = onClick,
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
private fun ReusableIconButtonPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableIconButton(
            text = "Add",
            icon = Icons.Default.Add,
            onClick = {}
        )
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun ReusableIconButtonDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        ReusableIconButton(
            text = "Add",
            icon = Icons.Default.Add,
            onClick = {}
        )
    }
}
