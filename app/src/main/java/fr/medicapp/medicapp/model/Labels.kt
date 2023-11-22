package fr.medicapp.medicapp.model

data class Labels(
    val bMedication: Label = Label("B-medication"),
    val iMedication: Label = Label("I-medication"),
    val bDuration: Label = Label("B-duration"),
    val iDuration: Label = Label("I-duration"),
    val bDoctor: Label = Label("B-doctor"),
    val iDoctor: Label = Label("I-doctor"),
    val bQuantity: Label = Label("B-quantity"),
    val iQuantity: Label = Label("I-quantity"),
    val none: Label = Label("O")
)