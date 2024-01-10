package fr.medicapp.medicapp.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class PrescriptionEntity(
    @PrimaryKey
    val id: String,

    var date: LocalDate,
)
