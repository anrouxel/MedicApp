package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.entity.Prescription

data class AddPrescription(
    var instructions: OptionButtonContent? = null,
    var source: Boolean? = null,
    var prescription: Prescription = Prescription()
)