package fr.medicapp.medicapp.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.Greeting
import fr.medicapp.medicapp.ui.theme.MedicAppTheme

@Composable
fun MedicAppButton() {
    Button(onClick = { /*TODO*/ }) {
        Text(text = "Button")
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonPreview() {
    MedicAppTheme {
        MedicAppButton()
    }
}