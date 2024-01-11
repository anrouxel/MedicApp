package fr.medicapp.medicapp.model

import androidx.compose.runtime.mutableStateListOf
import fr.medicapp.medicapp.entity.SideEffectEntity
import fr.medicapp.medicapp.entity.TreatmentEntity
import java.time.LocalDate
import java.util.UUID

data class SideEffect(
    var id: String = "",
    var medicament: TreatmentEntity? = null,
    var date: LocalDate? = null,
    var hour: Int? = null,
    var minute: Int? = null,
    var effetsConstates: MutableList<String> = mutableStateListOf(),
    var description: String = ""
) {
    fun toEntity(): SideEffectEntity {
        return SideEffectEntity(
            id = UUID.randomUUID().toString(),
            medicament = medicament!!.id,
            date = date!!,
            hour = hour!!,
            minute = minute!!,
            effetsConstates = effetsConstates,
            description = description
        )
    }
}
