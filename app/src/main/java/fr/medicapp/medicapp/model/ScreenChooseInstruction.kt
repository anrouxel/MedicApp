package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.ui.navigation.AddPrescriptionsRoute

object ScreenChooseInstruction {
    private val _options: HashMap<OptionInstruction, OptionButtonContent> = hashMapOf(
        OptionInstruction.OCR to OptionButtonContent(
            "Caméra",
            "Prendre une photo de l'ordonnance",
            AddPrescriptionsRoute.ChooseSource.route
        ),
        OptionInstruction.GALLERY to OptionButtonContent(
            "Galerie",
            "Choisir une photo de l'ordonnance",
            AddPrescriptionsRoute.ChooseSource.route
        ),
        OptionInstruction.MANUAL to OptionButtonContent(
            "Manuel",
            "Entrer manuellement les informations de l'ordonnance",
            AddPrescriptionsRoute.ChooseSource.route
        )
    )

    fun getOptions(): HashMap<OptionInstruction, OptionButtonContent> {
        return _options
    }
}
