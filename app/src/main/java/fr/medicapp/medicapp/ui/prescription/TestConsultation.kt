package fr.medicapp.medicapp.ui.prescription

import java.time.LocalDate
import java.util.Date

data class TestConsultation(
    var medecin: String,
    var date: String,
    var medicaments: List<TestMedicament>
)