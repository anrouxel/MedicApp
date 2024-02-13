package fr.medicapp.medicapp.model

import android.content.Context
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.SideEffectEntity
import java.time.LocalDate

data class SideEffect(
    val id: Long = 0L,

    var date: LocalDate? = null,

    var hour: Int = 0,

    var minute: Int = 0,

    var effetsConstates: MutableList<String> = mutableListOf(),

    var description: String = "",

    var treatment: Treatment? = null
) : ModelToEntityMapper<SideEffectEntity> {
    override fun convert(context: Context): SideEffectEntity {
        val sideEffect = SideEffectEntity(
            id,
            date,
            hour,
            minute,
            effetsConstates,
            description
        )

        val box = ObjectBox.getInstance(context)
        val store = box.boxFor(SideEffectEntity::class.java)
        store.attach(sideEffect)

        sideEffect.treatment.target = treatment?.convert(context)
        return sideEffect
    }
}
