package fr.medicapp.medicapp.ui.prescriptions

import androidx.navigation.NavHostController

sealed class AddPrescriptionOptionChooseInstruction(
    title: String,
    description: String,
    onClick: (NavHostController) -> Unit
) : AddPrescriptionOption(title, description, onClick) {
    object CAMERA : AddPrescriptionOption(
        title = "Caméra",
        description = "Prendre une photo",
        onClick = { }
    )

    object GALLERY : AddPrescriptionOption(
        title = "Galerie",
        description = "Sélectionner depuis la galerie",
        onClick = { }
    )

    object MANUAL : AddPrescriptionOption(
        title = "Manuel",
        description = "Entrer les détails manuellement",
        onClick = { }
    )
}
