package fr.medicapp.medicapp.entity

import androidx.compose.runtime.mutableStateListOf
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.UUID

@Entity(tableName = "Prescription")
data class Prescription(
    @PrimaryKey(autoGenerate = true) val id: UUID = UUID.randomUUID(),
    var doctor: Doctor? = null,
    var date: LocalDate? = null,
    var treatments: MutableList<Treatment> = mutableStateListOf(),
) {
    fun isPrescribedByDoctor(): Boolean {
        return doctor != null
    }

    fun isPrescribedByDoctor(doctor: Doctor?): Boolean {
        return this.doctor == doctor
    }

    fun isValide(): Boolean {
        return doctor != null && date != null && treatments.isNotEmpty() && areTreatmentsValid()
    }

    fun areTreatmentsValid(): Boolean {
        for (treatment in treatments) {
            if (!treatment.isValide()) {
                return false
            }
        }
        return true
    }
}
