package fr.medicapp.medicapp.model

data class AddPrescriptionOptionTextField(
    override val title: String,
    val placeholder: String,
    var value: String,
) : AddPrescriptionOption(title)
