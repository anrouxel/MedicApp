package fr.medicapp.medicapp.entity

data class MedicationCompositionEntity(
    var cisCode: Long,
    var pharmaceuticalElementDesignation: String,
    var substanceCode: Long,
    var substanceName: String,
    var substanceDosage: String,
    var dosageReference: String,
    var componentNature: String,
    var linkNumber: Int?
)
