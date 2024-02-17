package fr.medicapp.medicapp.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.ui.components.button.ReusableElevatedCardButton
import fr.medicapp.medicapp.ui.components.calendar.Calendar
import fr.medicapp.medicapp.ui.components.card.CardContent
import fr.medicapp.medicapp.ui.theme.EUPurpleColorShema
import fr.medicapp.medicapp.ui.theme.MedicAppTheme
import java.time.LocalDate
import java.time.LocalDateTime

@Composable
fun HomeScreen(
    onAddPrescriptionClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        ) {

        val context = LocalContext.current
        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(PrescriptionEntity::class.java)

        val prescriptions = store.all.map { it.convert() }

        val selection = remember { mutableStateOf(LocalDate.now()) }
        Calendar(selection = selection)
        Spacer(modifier = Modifier.height(8.dp))
        ListOfMedication(selectedDate = selection.value, prescription = prescriptions)
    }
}

@Composable
fun ListOfMedication(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate,
    prescription: List<Prescription>
) {

    val medocsForToday = prescription
        .map { it.getNotificationsDates(selectedDate) }
        .flatten()


    LazyColumn(modifier = modifier) {
        items(medocsForToday) {
            val name = it.prescription.treatment.medication.toString()
            val hourAndMinute = "${it.date.hour}h${it.date.minute.toString().padStart(2, '0')}"
            val enabled = it.date.isAfter(LocalDateTime.now())
            ReusableElevatedCardButton(enabled = enabled, onClick = {}) {
                CardContent(title = name, description = hourAndMinute)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MedicAppTheme(
        darkTheme = false,
        dynamicColor = false,
        theme = EUPurpleColorShema
    ) {
        HomeScreen(
            onAddPrescriptionClick = { }
        )
    }
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