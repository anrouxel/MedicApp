package fr.medicapp.medicapp.model

object AddPrescriptionOptionChooseInstruction : AddPrescriptionOptionsRadioButton() {

    override val _route: String = AddPrescriptionOptionChooseInstruction::class.java.name

    override val _title: String = "Comment voulez-vous ajouter l'ordonnance ?"

    override val _options: List<AddPrescriptionOption> = listOf(
        AddPrescriptionOptionRadioButton(
            "Caméra",
            "Prendre une photo de l'ordonnance",
            null
        ),
        AddPrescriptionOptionRadioButton(
            "Galerie",
            "Choisir une photo de l'ordonnance",
            null
        ),
        AddPrescriptionOptionRadioButton(
            "Manuel",
            "Entrer manuellement les informations de l'ordonnance",
            AddPrescriptionOptionPrescriptionSource.getRoute()
        )
    )
}
