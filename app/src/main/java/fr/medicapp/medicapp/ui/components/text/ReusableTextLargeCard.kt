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
fun ReusableTextLargeCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
) {
    Text(
        modifier = modifier,
        text = value,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
    )
}

@Preview(name = "Light Theme", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun ReusableTextLargeCardPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUPurpleColorShema,
        dynamicColor = false
    ) {
        ReusableTextLargeCard(
            value = "Hello",
        )
    }
}

@Preview(name = "Dark Theme", showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ReusableTextLargeCardDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUPurpleColorShema,
        dynamicColor = false
    ) {
        ReusableTextLargeCard(
            value = "Hello",
        )
    }
}
