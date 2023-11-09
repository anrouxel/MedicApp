package fr.medicapp.medicapp.ui.prescriptions

import androidx.navigation.NavHostController

sealed class AddPrescriptionOption(
    val title: String,
    val description: String,
    val onClick: (NavHostController) -> Unit
) {
    object NONE : AddPrescriptionOption(
        title = "",
        description = "",
        onClick = { }
    )
}