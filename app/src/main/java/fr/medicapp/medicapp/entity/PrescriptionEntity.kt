package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.database.LocalDateConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import io.objectbox.relation.ToOne
import java.time.LocalDate

/**
 * Entité représentant une prescription dans la base de données.
 *
 * @property id L'identifiant unique de la prescription.
 * @property date La date de la prescription.
 */
@Entity
data class PrescriptionEntity(

    /**
     * L'identifiant unique de la prescription.
     */
    @Id
    var id: Long = 0L,

    /**
     * La date de la prescription.
     */
    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var date: LocalDate? = null,
) {
    lateinit var doctor: ToOne<DoctorEntity>

    /**
     * La liste des traitements prescrits.
     */
    lateinit var treatments: ToMany<TreatmentEntity>
}
