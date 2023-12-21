package fr.medicapp.medicapp.entity

import androidx.room.Entity

/**
 * Table nommée Labels.
 * Elle créer uniquement des Label utilisés par le tri.
 * */
@Entity(tableName = "Labels")
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