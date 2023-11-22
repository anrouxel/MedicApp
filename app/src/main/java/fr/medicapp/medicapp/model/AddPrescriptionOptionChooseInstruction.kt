package fr.medicapp.medicapp.model

object AddPrescriptionOptionChooseInstruction : AddPrescriptionOptions() {

    override val route: String
        get() = this::class.java.name

    override val title: String = "Comment voulez-vous ajouter l'ordonnance ?"

    override val options: List<AddPrescriptionOption> = listOf(
        AddPrescriptionOption(
            "Caméra",
            "Prendre une photo de l'ordonnance",
            null
        ),
        AddPrescriptionOption(
            "Galerie",
            "Choisir une photo de l'ordonnance",
            null
        ),
        AddPrescriptionOption(
            "Manuel",
            "Entrer manuellement les informations de l'ordonnance",
            AddPrescriptionOptionPrescriptionSource.route
        )
    )
}