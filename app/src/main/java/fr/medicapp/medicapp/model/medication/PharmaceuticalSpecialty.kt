package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class PharmaceuticalSpecialty(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pharmaceutical_specialty_id")
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var cip13Code: String = "",

    var statusCode: Int = 0,

    var statusLabel: String = "",

    var startDate: LocalDate? = null,

    var updateDate: LocalDate? = null,

    var returnToDate: LocalDate? = null,

    var ansmSiteLink: String = "",
)