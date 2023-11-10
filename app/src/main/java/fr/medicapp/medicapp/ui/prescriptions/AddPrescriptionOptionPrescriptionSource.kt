package fr.medicapp.medicapp.ui.prescriptions

import androidx.navigation.NavHostController
import fr.medicapp.medicapp.ui.navigation.AddPrescriptionsRoute

sealed class AddPrescriptionOptionPrescriptionSource (
    title: String,
    description: String,
    onClick: (NavHostController) -> Unit
) : AddPrescriptionOption(title, description, onClick) {
    object DOCTOR_PRESCRIPTION : AddPrescriptionOption(
        title = "Oui, par mon médecin",
        description = "Le traitement a été prescrit par mon médecin",
        onClick = { navController ->
            navController.navigate(AddPrescriptionsRoute.AddPrescriptionsFrequency.route)
        }
    )

    object SELF_PRESCRIPTION : AddPrescriptionOption(
        title = "Non, auto-prescrit",
        description = "Je me suis auto-prescrit ce traitement",
        onClick = { navController ->
            navController.navigate(AddPrescriptionsRoute.AddPrescriptionsFrequency.route)
        }
    )
}
