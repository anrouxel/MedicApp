package fr.medicapp.medicapp.ui.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun CardContent(
    title: String,
    description: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontStyle = MaterialTheme.typography.bodyLarge.fontStyle,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = description,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,
            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
        )
    }
}

@Preview(name = "Light Theme")
@Composable
private fun CardContentPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        CardContent(
            title = "Médicament",
            description = "Posologie"
        )
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun CardContentDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        theme = EUYellowColorShema,
        dynamicColor = false
    ) {
        CardContent(
            title = "Médicament",
            description = "Posologie"
        )
    }
}
