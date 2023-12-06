package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.ui.navigation.AddPrescriptionsRoute

object ScreenSource {
    private val _options: HashMap<Boolean, OptionButtonContent> = hashMapOf(
        true to OptionButtonContent(
            "Oui",
            "Votre ordonnance a été prescrite par un médecin",
            AddPrescriptionsRoute.ChooseInstructions.route
        ),
        false to OptionButtonContent(
            "Non",
            "Votre ordonnance n'a pas été prescrite par un médecin",
            AddPrescriptionsRoute.ChooseInstructions.route
        ),
    )

    fun getOptions(): HashMap<Boolean, OptionButtonContent> {
        return _options
    }
}
