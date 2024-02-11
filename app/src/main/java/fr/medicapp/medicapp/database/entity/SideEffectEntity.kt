package fr.medicapp.medicapp.database.entity

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.LocalDateConverter
import fr.medicapp.medicapp.model.SideEffect
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import java.time.LocalDate

@Entity
data class SideEffectEntity(
    @Id
    var id: Long = 0L,

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var date: LocalDate? = null,

    var hour: Int = 0,

    var minute: Int = 0,

    var effetsConstates: MutableList<String> = mutableListOf(),

    var description: String = ""
) : EntityToModelMapper<SideEffect> {
    lateinit var treatment: ToOne<TreatmentEntity>

    override fun convert(): SideEffect {
        return SideEffect(
            id,
            date,
            hour,
            minute,
            effetsConstates,
            description
        )
    }
}
