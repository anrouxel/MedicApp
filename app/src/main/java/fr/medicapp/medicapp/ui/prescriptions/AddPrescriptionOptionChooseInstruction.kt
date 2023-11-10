package fr.medicapp.medicapp.ui.prescriptions

import androidx.navigation.NavHostController
import fr.medicapp.medicapp.ui.navigation.AddPrescriptionsRoute

sealed class AddPrescriptionOptionChooseInstruction(
    title: String,
    description: String,
    onClick: (NavHostController) -> Unit
) : AddPrescriptionOption(title, description, onClick) {
    object CAMERA : AddPrescriptionOption(
        title = "Caméra",
        description = "Prendre une photo",
        onClick = { navController -> /* Handle navigation or other logic */  }
    )

    object GALLERY : AddPrescriptionOption(
        title = "Galerie",
        description = "Sélectionner depuis la galerie",
        onClick = { navController -> /* Handle navigation or other logic */  }
    )

    object MANUAL : AddPrescriptionOption(
        title = "Manuel",
        description = "Entrer les détails manuellement",
        onClick = { navController ->
            navController.navigate(AddPrescriptionsRoute.AddPrescriptionsSource.route)
        }
    )
}
