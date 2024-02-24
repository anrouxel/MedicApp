package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class ImportantInformation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "important_information_id")
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var safetyInformationStartDate: LocalDate? = null,

    var safetyInformationEndDate: LocalDate? = null,

    var safetyInformationLink: String = ""
)
