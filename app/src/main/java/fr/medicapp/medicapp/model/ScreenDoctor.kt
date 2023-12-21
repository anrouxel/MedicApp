package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.ui.navigation.AddPrescriptionsRoute

object ScreenDoctor {
    private val _options: HashMap<OptionDoctor, OptionButtonContent> = hashMapOf(
        OptionDoctor.MOTTU to OptionButtonContent(
            "Dr Mottu",
            null,
            AddPrescriptionsRoute.ChooseInstructions.route
        ),
        OptionDoctor.CAZALAS to OptionButtonContent(
            "Dr Cazalas",
            null,
            AddPrescriptionsRoute.ChooseInstructions.route
        ),
        OptionDoctor.NACHOUKI to OptionButtonContent(
            "Dr Nachouki",
            null,
            AddPrescriptionsRoute.ChooseInstructions.route
        ),
    )

    fun getOptions(): HashMap<OptionDoctor, OptionButtonContent> {
        return _options
    }
}
