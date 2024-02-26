package fr.medicapp.medicapp.model.prescription

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class PrescriptionInformation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "prescription_id")
    val id: Long = 0L,

    var posology: String = "",

    var frequency: String = "",

    var takes: MutableList<LocalDateTime> = mutableListOf(),

    @ColumnInfo(name = "doctor_id")
    var doctorId: Long = 0L,

    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L,

    @ColumnInfo(name = "duration_id")
    var durationId: Long = 0L
)
