package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.entity.MedicationEntity
import fr.medicapp.medicapp.entity.TreatmentEntity
import java.util.UUID

/**
 * Modèle représentant un traitement.
 *
 * @property id L'identifiant unique du traitement.
 * @property medication Le médicament associé au traitement.
 * @property posology La posologie du traitement.
 * @property quantity La quantité du traitement.
 * @property renew Le renouvellement du traitement.
 * @property duration La durée du traitement.
 * @property notification La notification du traitement.
 * @property query La requête du traitement.
 */
data class Treatment(

    /**
     * L'identifiant unique du traitement.
     */
    var id: String = "",

    /**
     * Le médicament associé au traitement.
     */
    var medication: MedicationEntity? = null,

    /**
     * La posologie du traitement.
     */
    var posology: String = "",

    /**
     * La quantité du traitement.
     */
    var quantity: String = "",

    /**
     * Le renouvellement du traitement.
     */
    var renew: String = "",

    /**
     * La durée du traitement.
     */
    var duration: Duration? = null,

    /**
     * La notification du traitement.
     */
    var notification: Boolean = false,

    /**
     * La requête du traitement.
     */
    var query: String = ""
) {

    /**
     * Convertit ce traitement en une option de dialogue.
     *
     * @return Une option de dialogue correspondant à ce traitement.
     */
    fun toOptionDialog(): OptionDialog {
        return OptionDialog(
            id = id,
            title = medication!!.name,
            description = posology,
        )
    }

    /**
     * Convertit ce traitement en une entité TreatmentEntity.
     *
     * @return Une entité TreatmentEntity correspondant à ce traitement.
     */
    fun toEntity(): TreatmentEntity {
        return TreatmentEntity(
            id = if (id.isEmpty()) UUID.randomUUID().toString() else id,
            medication = medication!!.cisCode,
            posology = posology,
            quantity = quantity,
            renew = renew,
            duration = duration?.toEntity()!!,
            notification = notification
        )
    }
}
