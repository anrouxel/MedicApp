package fr.medicapp.medicapp.model

data class AddPrescription(
    var chooseInstructionss: MutableList<AddPrescriptionOptionChooseInstruction?> = mutableListOf(),
    var prescriptionSource: AddPrescriptionOptionPrescriptionSource? = null,
)
