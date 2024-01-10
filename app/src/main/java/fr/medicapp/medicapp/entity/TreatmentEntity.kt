package fr.medicapp.medicapp.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.Date
import java.util.UUID

@Entity
data class TreatmentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @Embedded
    val duration: DurationEntity,
)