package fr.medicapp.medicapp.ui.components.card

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.theme.EUYellowColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableElevatedCard(
    modifier: Modifier = Modifier.fillMaxWidth(),
    content: @Composable ColumnScope.() -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier,
        content = content,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 6.dp,
            disabledElevation = 6.dp,
            focusedElevation = 6.dp,
            hoveredElevation = 6.dp,
            draggedElevation = 6.dp
        ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.surface
        ),

        )
}

@Preview(name = "Light Theme")
@Composable
private fun ReusableElevatedCardPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        ReusableElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CardContent(
                title = "Médicament",
                description = "Posologie"
            )
        }
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun ReusableElevatedCardDarkPreview() {
    MedicAppTheme(
        darkTheme = true,
        dynamicColor = false,
        theme = EUYellowColorShema
    ) {
        ReusableElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CardContent(
                title = "Médicament",
                description = "Posologie"
            )
        }
    }
}
