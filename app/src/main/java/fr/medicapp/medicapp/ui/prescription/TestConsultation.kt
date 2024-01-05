package fr.medicapp.medicapp.ui.prescription

data class TestConsultation (
    var medecin : String,
    var date : String,
    var medicaments : List<TestMedicament>
)