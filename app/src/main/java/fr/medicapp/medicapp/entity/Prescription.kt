package fr.medicapp.medicapp.entity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import java.time.LocalDate
import java.util.Date

data class Prescription(
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
}
