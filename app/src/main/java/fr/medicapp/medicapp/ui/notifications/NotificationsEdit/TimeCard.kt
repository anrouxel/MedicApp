package fr.medicapp.medicapp.ui.notifications.NotificationsEdit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeCard(
    heure: String
) {
    var heureR = remember { mutableStateOf(heure) }

    OutlinedTextField(
        value = heure,
        textStyle = TextStyle(
            fontSize = 16.sp,
            color = Color.White
        ),
        onValueChange = { },
        shape = RoundedCornerShape(20),
        trailingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.DeleteForever,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
        ),
        modifier = Modifier.fillMaxWidth()
    )

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TimeCardPreview() {
    TimeCard(
        ""
    )
}