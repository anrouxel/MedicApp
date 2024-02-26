package fr.medicapp.medicapp.model.prescription

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class SideEffectInformation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "side_effect_id")
    val id: Long = 0L,

    var date: LocalDate? = null,

    var description: String = "",

    @ColumnInfo(name = "prescription_id")
    var prescriptionId: Long = 0L
)
