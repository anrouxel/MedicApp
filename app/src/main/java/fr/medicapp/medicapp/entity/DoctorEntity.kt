package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maxkeppeler.sheets.option.models.Option
import java.util.UUID

@Entity
data class DoctorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val lastName: String,

    var firstName: String,
)
