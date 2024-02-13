package fr.medicapp.medicapp.ui.components.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun ReusableTextMediumCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
) {
    Text(
        modifier = modifier,
        text = value,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        fontStyle = MaterialTheme.typography.bodyMedium.fontStyle,
        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
    )
}

@Preview(name = "Light Theme", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ReusableTextMediumCardPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUPurpleColorShema,
        dynamicColor = false
    ) {
        ReusableTextMediumCard(
            value = "Hello",
        )
    }
}

@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ReusableTextMediumCardDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUPurpleColorShema,
        dynamicColor = false
    ) {
        ReusableTextMediumCard(
            value = "Hello",
        )
    }
}
