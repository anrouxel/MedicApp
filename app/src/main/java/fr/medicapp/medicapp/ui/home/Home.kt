package fr.medicapp.medicapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.ui.theme.EUGreen100
import fr.medicapp.medicapp.ui.theme.EUGreen120

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ElevatedCard(
            onClick = { /*TODO*/ },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = EUGreen120,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
            ) {
                Text(
                    text = "Bonjour, John Doe",
                )
            }
        }
        Button(
            onClick = {
                showBottomSheet = true
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUGreen100
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Ajouter une ordonnance")
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = EUGreen100
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Signaler un effet indesirable")
        }
    }
    if (showBottomSheet) {
        AddPrescriptionModalBottomSheet(
            onDismissRequest = { showBottomSheet = false }
        )
    }
}

@Preview
@Composable
private fun HomePreview() {
    Home()
}
