package fr.medicapp.medicapp.ui.notifications.NotificationsEdit

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import fr.medicapp.medicapp.ui.prescription.TimePickerModal

/**
 * Cette fonction affiche une carte avec l'heure et la minute spécifiées.
 *
 * @param hour L'heure à afficher sur la carte.
 * @param minute La minute à afficher sur la carte.
 */
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeCard(
    hour: Int,
    minute: Int
) {
    var hourR = remember { mutableStateOf(hour) }

    var frequencyTimeOpen = remember { mutableStateOf(false) }
    var frequencyTimeState = rememberTimePickerState(
        is24Hour = true,
    )

    if (frequencyTimeOpen.value) {
        TimePickerModal(
            state = frequencyTimeState,
            onDismissRequest = {
                frequencyTimeOpen.value = false
            },
            onConfirm = {
                val hour = frequencyTimeState.hour
                Log.d("Hour", hour.toString())
                val minute = frequencyTimeState.minute
                Log.d("Minute", minute.toString())
                frequencyTimeOpen.value = false
            }
        )
    }

    OutlinedTextField(
        modifier = Modifier
            .clickable {
                frequencyTimeOpen.value = true
            }
            .fillMaxWidth(),
        enabled = false,
        value = if (hour != null && minute != null) "${hour}h${minute}" else "",
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
            disabledBorderColor = Color.White,
            disabledLabelColor = Color.White,
        )
    )

    /*OutlinedTextField(
        value = hour,
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
    )*/

}

/**
 * Cette fonction affiche un aperçu de la carte de temps.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TimeCardPreview() {
    TimeCard(
        23,
        45
    )
}