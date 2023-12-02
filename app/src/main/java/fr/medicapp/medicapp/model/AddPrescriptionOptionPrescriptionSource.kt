package fr.medicapp.medicapp.model

object AddPrescriptionOptionPrescriptionSource : AddPrescriptionOptionsRadioButton() {

    override val _route: String = AddPrescriptionOptionPrescriptionSource::class.java.name

    override val _title: String = "Votre prescription a-t-elle été faite par un médecin ?"

    override val _options: List<AddPrescriptionOption> = listOf(
        AddPrescriptionOptionRadioButton(
            "Oui",
            "Votre prescription a été faite par un médecin",
            AddPrescriptionOptionDoctorInfo.getRoute()
        ),
        AddPrescriptionOptionRadioButton(
            "Non",
            "Votre prescription n'a pas été faite par un médecin",
            null
        )
    )
}
