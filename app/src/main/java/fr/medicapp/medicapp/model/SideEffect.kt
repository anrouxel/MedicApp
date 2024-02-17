package fr.medicapp.medicapp.model

import android.content.Context
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.SideEffectEntity
import java.time.LocalDate

data class SideEffect(
    val id: Long = 0L,

    var date: LocalDate? = null,

    var description: String = "",

    var prescription: Prescription? = null
) : ModelToEntityMapper<SideEffectEntity> {
    override fun convert(context: Context): SideEffectEntity {
        val sideEffect = SideEffectEntity(
            id,
            date,
            description
        )

        val box = ObjectBox.getInstance(context)
        val store = box.boxFor(SideEffectEntity::class.java)
        store.attach(sideEffect)

        sideEffect.prescription.target = prescription?.convert(context)
        return sideEffect
    }

    fun convertBacklink(context: Context): SideEffectEntity {
        val sideEffect = SideEffectEntity(
            id,
            date,
            description
        )

        val box = ObjectBox.getInstance(context)
        val store = box.boxFor(SideEffectEntity::class.java)
        store.attach(sideEffect)

        sideEffect.prescription.target = prescription?.convertBacklink(context)
        return sideEffect
    }
}
