package fr.medicapp.medicapp.ui.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun ReusableButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    ButtonCard(
        modifier = modifier,
        onClick = onClick,
        content = {
            ButtonContent(
                text = text
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
