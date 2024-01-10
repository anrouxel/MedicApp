package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.Date
import java.util.UUID

@Entity
data class Frequency(
    @PrimaryKey(autoGenerate = true) val id: UUID = UUID.randomUUID(),
    var day: Int? = null,
    var hour: Int? = null,
    var minute: Int? = null,
    ) {
    /*init {
        require(hour in 0..23) { "Heure comprise entre 0 et 23" }
        require(day in 1..7) { "Jour de la semaine compris entre 0 et 6" }
    }*/

    fun isValide(): Boolean {
        return day != null && hour != null && minute != null
    }
}
