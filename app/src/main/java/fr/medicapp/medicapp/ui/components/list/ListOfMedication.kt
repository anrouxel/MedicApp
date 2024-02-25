package fr.medicapp.medicapp.ui.components.list


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.medicapp.medicapp.model.prescription.relationship.Prescription
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.LocalDate

@Composable
fun ListOfMedication(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    prescription: List<Prescription>
) {

    //val medocsForToday = emptyList()


    /*LazyColumn(modifier = modifier) {
        items(medocsForToday) {
            val name = ""
            val hourAndMinute = "12h00"
            val enabled = LocalDateTime.now()
            ReusableElevatedCardButton(enabled = true, onClick = {}) {
                CardContent(title = name, description = hourAndMinute)
            }
        }
    }*/
}

@Preview
@Composable
fun PreviewListOfMedication() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        ListOfMedication(selectedDate = LocalDate.now(), prescription = listOf())
    }
}

