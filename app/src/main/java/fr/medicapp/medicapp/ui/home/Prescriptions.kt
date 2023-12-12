package fr.medicapp.medicapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.R

@Composable
fun Prescriptions() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.medicapp_eu_green),
            contentDescription = "Logo"
        )
    }
}

@Preview
@Composable
private fun PreviewPrescriptions() {
    Prescriptions()
}
