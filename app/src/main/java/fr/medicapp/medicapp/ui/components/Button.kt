package fr.medicapp.medicapp.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.Greeting
import fr.medicapp.medicapp.ui.theme.CaribbeanGreen500
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import fr.medicapp.medicapp.ui.theme.White

@Composable
fun MedicAppButton() {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = CaribbeanGreen500,
            contentColor = White)
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