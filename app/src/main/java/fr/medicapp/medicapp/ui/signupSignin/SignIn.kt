package fr.medicapp.medicapp.ui.signupSignin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.ui.theme.EUGreen100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn() {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Box(
                modifier = Modifier.fillMaxSize().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.medicapp_eu_green),
                    contentDescription = "Logo"
                )
            }

            Text(
                "Bienvenue sur MedicApp",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it},
                label = { Text("Adresse mail") },
                shape = RoundedCornerShape(20),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = EUGreen100,
                    unfocusedBorderColor = EUGreen100,
                ),
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)
            )

            OutlinedTextField(
                value = text,
                onValueChange = { text = it},
                label = { Text("Mot de passe") },
                shape = RoundedCornerShape(20),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = EUGreen100,
                    unfocusedBorderColor = EUGreen100,
                ),
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = EUGreen100,
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth().padding(start = 30.dp, end = 30.dp, top = 30.dp)
            ) {
                Text(
                    text = "Connexion",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                "Créer un compte",
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                color = EUGreen100,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                "Mot de passe oublié ?",
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                color = EUGreen100,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInPreview() {
    SignIn()
}



