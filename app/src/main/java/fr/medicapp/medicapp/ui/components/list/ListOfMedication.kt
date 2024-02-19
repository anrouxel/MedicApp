package fr.medicapp.medicapp.ui.components.list


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.ui.components.button.ReusableElevatedCardButton
import fr.medicapp.medicapp.ui.components.card.CardContent
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun ListOfMedication(
    modifier : Modifier = Modifier,
    selectedDate : LocalDate,
    prescription : List<Prescription>
) {

    val medocsForToday = prescription
        .map { it.getNotificationsDates(selectedDate) }
        .flatten()


    LazyColumn(modifier=modifier){
        items(medocsForToday) {
            val name = it.prescription.treatment.medication.toString()
            val hourAndMinute = "${it.date.hour}h${it.date.minute.toString().padStart(2, '0')}"
            val enabled = it.date.isAfter(LocalDateTime.now())
            ReusableElevatedCardButton(enabled = enabled, onClick = {}) {
                CardContent(title =name, description = hourAndMinute)
            }
        }
    }
}

@Preview
@Composable
fun PreviewListOfMedication(){
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        ListOfMedication(selectedDate = LocalDate.now(), prescription = listOf())
    }
}

