package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.medicapp.medicapp.model.SideEffect
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
    @PrimaryKey
    var id: String,

    /**
     * Le nom du médicament associé à l'effet secondaire.
     */
    var medicament: String,

    /**
     * La date de l'effet secondaire.
     */
    var date: LocalDate,

    /**
     * L'heure de l'effet secondaire.
     */
    var hour: Int,

    /**
     * La minute de l'effet secondaire.
     */
    var minute: Int,

    /**
     * Les effets constatés.
     */
    var effetsConstates: MutableList<String>,

    /**
     * La description de l'effet secondaire.
     */
    var description: String
) {

    /**
     * Convertit cette entité en un objet SideEffect.
     *
     * @return Un objet SideEffect correspondant à cette entité.
     */
    fun toSideEffect(): SideEffect {
        return SideEffect(
            id = id,
            medicament = null,
            date = date,
            hour = hour,
            minute = minute,
            effetsConstates = effetsConstates,
            description = description
        )
    }
}
