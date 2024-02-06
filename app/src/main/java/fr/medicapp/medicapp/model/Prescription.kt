package fr.medicapp.medicapp.model

import androidx.compose.runtime.mutableStateListOf
import fr.medicapp.medicapp.entity.TreatmentEntity
import java.time.LocalDate

/**
 * Modèle représentant une prescription.
 *
 * @property id L'identifiant unique de la prescription.
 * @property doctor Le docteur qui a émis la prescription.
 * @property date La date de la prescription.
 * @property treatments Les traitements inclus dans la prescription.
 */
data class Prescription(

    /**
     * L'identifiant unique de la prescription.
     */
    var id: String? = null,

    /**
     * Le docteur qui a émis la prescription.
     */
    var doctor: Doctor? = null,

    /**
     * La date de la prescription.
     */
    var date: LocalDate? = null,

    /**
     * Les traitements inclus dans la prescription.
     */
    var treatments: MutableList<TreatmentEntity> = mutableStateListOf()
) {

    /**
     * Vérifie si la prescription est valide.
     *
     * @return `true` si la prescription est valide, `false` sinon.
     */
    fun isValide(): Boolean {
        return doctor != null && date != null && treatments.isNotEmpty()
    }
}
