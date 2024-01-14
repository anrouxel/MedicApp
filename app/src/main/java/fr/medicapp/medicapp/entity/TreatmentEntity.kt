package fr.medicapp.medicapp.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.repository.MedicationRepository

/**
 * Entité représentant un traitement dans la base de données.
 *
 * @property id L'identifiant unique du traitement.
 * @property medication Le nom du médicament associé au traitement.
 * @property posology La posologie du traitement.
 * @property quantity La quantité du traitement.
 * @property renew Le renouvellement du traitement.
 * @property duration La durée du traitement.
 * @property notification Indique si une notification est associée au traitement.
 */
@Entity
data class TreatmentEntity(

    /**
     * L'identifiant unique du traitement.
     */
    @PrimaryKey
    val id: String,

    /**
     * Le nom du médicament associé au traitement.
     */
    var medication: String,

    /**
     * La posologie du traitement.
     */
    var posology: String,

    /**
     * La quantité du traitement.
     */
    var quantity: String,

    /**
     * Le renouvellement du traitement.
     */
    var renew: String,

    /**
     * La durée du traitement.
     */
    @Embedded
    val duration: DurationEntity,

    /**
     * Indique si une notification est associée au traitement.
     */
    var notification: Boolean = false
) {

    /**
     * Convertit cette entité en un objet Treatment.
     *
     * @param repositoryMedication Le référentiel des médicaments.
     * @return Un objet Treatment correspondant à cette entité.
     */
    fun toTreatment(repositoryMedication: MedicationRepository): Treatment {
        return Treatment(
            id = id,
            medication = repositoryMedication.getOne(medication),
            posology = posology,
            quantity = quantity,
            renew = renew,
            duration = duration.toDuration(),
            notification = notification
        )
    }
}
