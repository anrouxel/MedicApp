package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.medicapp.medicapp.model.SideEffect
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
) {
    fun toSideEffect(): SideEffect {
        return SideEffect(
            id = id,
            medicament = null,
            date = date,
            hour = hour,
            minute = minute,
            effetsConstates = effetsConstates,
            description = description
        )
    }
}
