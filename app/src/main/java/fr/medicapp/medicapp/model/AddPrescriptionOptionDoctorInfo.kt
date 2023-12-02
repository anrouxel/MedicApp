package fr.medicapp.medicapp.model

object AddPrescriptionOptionDoctorInfo : AddPrescriptionOptionsTextField() {

    override val _route: String = AddPrescriptionOptionDoctorInfo::class.java.name

    override val _nextRoute: String = AddPrescriptionOptionChooseInstruction.getRoute()

    override val _title: String = "Informations sur le médecin"

    override val _options: List<AddPrescriptionOption> = listOf(
        AddPrescriptionOptionTextField(
            "Nom du médecin",
            "",
            ""
        ),
        AddPrescriptionOptionTextField(
            "Prénom du médecin",
            "",
            ""
        ),
    )
}
