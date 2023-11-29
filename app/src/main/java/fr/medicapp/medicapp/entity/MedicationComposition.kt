package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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