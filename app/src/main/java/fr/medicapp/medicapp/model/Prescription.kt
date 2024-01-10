package fr.medicapp.medicapp.model

import androidx.compose.runtime.mutableStateListOf
import java.time.LocalDate

data class Prescription(
    var id: String? = null,
    var doctor: Doctor? = null,
    var date: LocalDate? = null,
    var treatments: MutableList<Treatment> = mutableStateListOf()
) {
    fun isValide(): Boolean {
        return doctor != null && date != null && treatments.isNotEmpty()
    }
}
