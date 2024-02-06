package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.database.LocalDateConverter
import fr.medicapp.medicapp.entity.medication.MedicationEntity
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import java.time.LocalDate

/**
 * Entité représentant un effet secondaire dans la base de données.
 *
 * @property id L'identifiant unique de l'effet secondaire.
 * @property medicament Le nom du médicament associé à l'effet secondaire.
 * @property date La date de l'effet secondaire.
 * @property hour L'heure de l'effet secondaire.
 * @property minute La minute de l'effet secondaire.
 * @property effetsConstates Les effets constatés.
 * @property description La description de l'effet secondaire.
 */
@Entity
data class SideEffectEntity(

    /**
     * L'identifiant unique de l'effet secondaire.
     */
    @Id
    var id: Long = 0L,

    /**
     * La date de l'effet secondaire.
     */
    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var date: LocalDate? = null,

    /**
     * L'heure de l'effet secondaire.
     */
    var hour: Int= 0,

    /**
     * La minute de l'effet secondaire.
     */
    var minute: Int= 0,

    /**
     * Les effets constatés.
     */
    var effetsConstates: MutableList<String> = mutableListOf(),

    /**
     * La description de l'effet secondaire.
     */
    var description: String = ""
) {

    /**
     * Le nom du médicament associé à l'effet secondaire.
     */
    lateinit var medicament: ToOne<MedicationEntity>
}
