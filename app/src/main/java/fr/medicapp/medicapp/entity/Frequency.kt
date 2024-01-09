package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.util.UUID

@Entity
data class Frequency(
    @PrimaryKey(autoGenerate = true) val id: UUID = UUID.randomUUID(),
    var hour: Int? = null,
    var day: Int? = null,
) {
    fun isValide(): Boolean {
        return hour != null && day != null
    }
}
