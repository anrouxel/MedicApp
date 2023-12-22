package fr.medicapp.medicapp.entity

data class Prescription(
    var doctor: Doctor? = null,
    val treatments: List<Treatment> = emptyList(),
) {
    fun isPrescribedByDoctor(): Boolean {
        return doctor != null
    }

    fun isPrescribedByDoctor(doctor: Doctor?): Boolean {
        return this.doctor == doctor
    }
}
