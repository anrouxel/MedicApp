package fr.medicapp.medicapp.ui.prescriptions

import androidx.navigation.NavHostController
import fr.medicapp.medicapp.ui.navigation.AddPrescriptionsRoute

class AddPrescriptionOptionFrequency (
    title: String,
    description: String,
    onClick: (NavHostController) -> Unit
) : AddPrescriptionOption(title, description, onClick) {
    object ONCE_DAILY : AddPrescriptionOption(
        title = "Une fois par jour",
        description = "Prendre le traitement une fois par jour",
        onClick = { navController ->
            navController.navigate(AddPrescriptionsRoute.AddPrescriptionsSource.route)
        }
    )

    object TWICE_DAILY : AddPrescriptionOption(
        title = "Deux fois par jour",
        description = "Prendre le traitement deux fois par jour",
        onClick = { navController -> /* Handle navigation or other logic */ }
    )

    object CUSTOM_FREQUENCY : AddPrescriptionOption(
        title = "Personnalisé",
        description = "Configurer une fréquence personnalisée",
        onClick = { navController -> /* Handle navigation or other logic */ }
    )
}
