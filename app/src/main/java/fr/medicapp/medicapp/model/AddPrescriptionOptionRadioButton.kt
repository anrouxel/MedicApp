package fr.medicapp.medicapp.model

data class AddPrescriptionOptionRadioButton(
    override val title: String,
    val description: String,
    val route: String?
) : AddPrescriptionOption(title)
