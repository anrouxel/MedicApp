package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MedicationComposition(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "medication_composition_id")
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var pharmaceuticalElementDesignation: String = "",

    var substanceCode: Long = 0L,

    var substanceName: String = "",

    var substanceDosage: String = "",

    var dosageReference: String = "",

    var componentNature: String = "",

    var linkNumber: Int? = null,

    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L
)