package fr.medicapp.medicapp.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun MedicAppButton() {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = EUGreen100,
            contentColor = Color.White
        )
    ) {
        Text(text = "Connexion")
    }
}

@Preview(showBackground = false)
@Composable
private fun ButtonPreview() {
    MedicAppTheme {
        MedicAppButton()
    }
}