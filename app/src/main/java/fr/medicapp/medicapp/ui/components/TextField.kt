package fr.medicapp.medicapp.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.ui.theme.CaribbeanGreen500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicAppTextField() {
    var text by rememberSaveable {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it},
        label = { Text("Email") },
        shape = RoundedCornerShape(20),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = CaribbeanGreen500,
            unfocusedBorderColor = CaribbeanGreen500,
        )
    )
}

@Preview(showBackground = false)
@Composable
private fun TextFieldPreview() {
    MedicAppTextField()
}