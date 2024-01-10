package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class SideEffectEntity(
    @PrimaryKey
    var id: String,

    var medicament: String,

    var date: LocalDate,

    var hour: Int,

    var minute: Int,

    var effetsConstates: MutableList<String>,

    var description: String
)
