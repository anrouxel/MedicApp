package fr.medicapp.medicapp.modelDAO

data class MedicationComposition(
    val cisCode: String,
    val pharmaceuticalElementDesignation: String,
    val substanceCode: String,
    val substanceName: String,
    val substanceDosage: String,
    val dosageReference: String,
    val componentNature: String,
    val linkNumber: Int?
)