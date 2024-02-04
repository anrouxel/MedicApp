package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.database.LocalDateConverter
import fr.medicapp.medicapp.model.Treatment
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne
import java.time.LocalDate

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
    @Id
    var id: Long = 0L,

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
     * Indique si une notification est associée au traitement.
     */
    var notification: Boolean = false
) {
    /**
     * Le nom du médicament associé au traitement.
     */
    lateinit var medication: ToOne<MedicationEntity>

    /**
     * La durée du traitement.
     */
    lateinit var duration: ToOne<DurationEntity>
}
