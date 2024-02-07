package fr.medicapp.medicapp.model

import androidx.compose.runtime.mutableStateListOf
import fr.medicapp.medicapp.entity.SideEffectEntity
import java.time.LocalDate
import java.util.UUID

/**
 * Modèle représentant un effet secondaire.
 *
 * @property id L'identifiant unique de l'effet secondaire.
 * @property medicament Le traitement associé à l'effet secondaire.
 * @property date La date de l'effet secondaire.
 * @property hour L'heure de l'effet secondaire.
 * @property minute La minute de l'effet secondaire.
 * @property effetsConstates Les effets constatés de l'effet secondaire.
 * @property description La description de l'effet secondaire.
 */
data class SideEffect(

    /**
     * L'identifiant unique de l'effet secondaire.
     */
    var id: Long = 0L,

    /**
     * Le traitement associé à l'effet secondaire.
     */
    var medicament: Treatment? = null,

    /**
     * La date de l'effet secondaire.
     */
    var date: LocalDate? = null,

    /**
     * L'heure de l'effet secondaire.
     */
    var hour: Int? = null,

    /**
     * La minute de l'effet secondaire.
     */
    var minute: Int? = null,

    /**
     * Les effets constatés de l'effet secondaire.
     */
    var effetsConstates: MutableList<String> = mutableStateListOf(),

    /**
     * La description de l'effet secondaire.
     */
    var description: String = ""
) {

    /**
     * Convertit cet effet secondaire en une entité SideEffectEntity.
     *
     * @return Une entité SideEffectEntity correspondant à cet effet secondaire.
     */
//    fun toEntity(): SideEffectEntity {
//        return SideEffectEntity(
//            id = (if (id==0L) UUID.randomUUID() else id) as Long,
//            medicament = medicament!!.id,
//            date = date!!,
//            hour = hour!!,
//            minute = minute!!,
//            effetsConstates = effetsConstates.toMutableList(),
//            description = description
//        )
//    }
}
