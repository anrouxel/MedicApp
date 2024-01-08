package fr.medicapp.medicapp.ui.prescription.EditPrescription

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.date_time.DateTimeDialog
import com.maxkeppeler.sheets.date_time.models.DateTimeSelection
import com.maxkeppeler.sheets.option.OptionDialog
import com.maxkeppeler.sheets.option.models.Option
import com.maxkeppeler.sheets.option.models.OptionSelection
import fr.medicapp.medicapp.entity.Frequency
import fr.medicapp.medicapp.ui.theme.EUPurple100
import fr.medicapp.medicapp.ui.theme.EUPurple60
import fr.medicapp.medicapp.ui.theme.EURed60

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FrequencyCard(
    frequency: Frequency,
    onRemove: () -> Unit
) {
    var frequencyDayOpen = remember { mutableStateOf(false) }
    var frequencyHourOpen = remember { mutableStateOf(false) }

    val options = listOf(
        Option(
            titleText = "Lundi",
        ),
        Option(
            titleText = "Mardi",
        ),
        Option(
            titleText = "Mercredi",
        ),
        Option(
            titleText = "Jeudi",
        ),
        Option(
            titleText = "Vendredi",
        ),
        Option(
            titleText = "Samedi",
        ),
        Option(
            titleText = "Dimanche",
        ),
    )

    if (frequencyDayOpen.value) {
        OptionDialog(
            state = rememberUseCaseState(
                true,
                onCloseRequest = {
                    frequencyDayOpen.value = false
                }),
            selection = OptionSelection.Single(options) { index, option ->
                frequency.day = index + 1
            },
        )
    }

    if (frequencyHourOpen.value) {
        DateTimeDialog(
            state = rememberUseCaseState(
                true,
                onCloseRequest = {
                    frequencyHourOpen.value = false
                }),
            selection = DateTimeSelection.Time {
                frequency.hour = it.hour
            }
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                onRemove()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = EUPurple60,
                contentColor = Color.White,
            ),
            shape = RoundedCornerShape(30),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .padding(8.dp, end = 15.dp)
                .size(30.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "",
                tint = Color.White,
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        OutlinedTextField(
            enabled = false,
            value = frequency.day.toString(),
            textStyle = TextStyle(fontSize = 16.sp),
            onValueChange = { },
            label = { Text("Jour") },
            shape = RoundedCornerShape(20),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = EUPurple100,
                unfocusedBorderColor = EUPurple100,
                disabledBorderColor = EUPurple100,
                errorBorderColor = EURed60,
                focusedLabelColor = EUPurple100,
                unfocusedLabelColor = EUPurple100,
                disabledLabelColor = EUPurple100,
                errorLabelColor = EURed60,
            ),
            modifier = Modifier
                .weight(1f)
                .clickable {
                    frequencyDayOpen.value = true
                }
        )
        Spacer(modifier = Modifier.width(10.dp))
        OutlinedTextField(
            enabled = false,
            value = frequency.hour.toString(),
            textStyle = TextStyle(fontSize = 16.sp),
            onValueChange = { },
            label = { Text("Heure") },
            shape = RoundedCornerShape(20),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = EUPurple100,
                disabledBorderColor = EUPurple100,
                unfocusedBorderColor = EUPurple100,
                disabledLabelColor = EUPurple100,
            ),
            modifier = Modifier
                .weight(1f)
                .clickable {
                    frequencyHourOpen.value = true
                }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun FrequencyCardPreview() {
    FrequencyCard(
        frequency = Frequency(
            hour = 12,
            day = 1,
        ),
        onRemove = {}
    )
}