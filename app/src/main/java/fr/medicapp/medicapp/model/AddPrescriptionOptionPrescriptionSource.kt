package fr.medicapp.medicapp.model

object AddPrescriptionOptionPrescriptionSource: AddPrescriptionOptions() {

    override val route: String
        get() = this::class.java.name

    override val title: String = "Votre prescription a-t-elle été faite par un médecin ?"

    override val options: List<AddPrescriptionOption> = listOf(
        AddPrescriptionOption(
            "Oui",
            "Votre prescription a été faite par un médecin",
            null
        ),
        AddPrescriptionOption(
            "Non",
            "Votre prescription n'a pas été faite par un médecin",
            null
        )
    )
}
