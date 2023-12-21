package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table nommée MedicationComposition.
 * Définie par son cisCode, elle est utilisée par Medication.
 * */
@Entity(tableName = "MedicationComposition")
data class MedicationComposition(
    @PrimaryKey val cisCode: String,
    val pharmaceuticalElementDesignation: String,
    val substanceCode: String,
    val substanceName: String,
    val substanceDosage: String,
    val dosageReference: String,
    val componentNature: String,
    val linkNumber: Int?
)