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

    var description: String = ""
) : EntityToModelMapper<SideEffect> {
    lateinit var prescription: ToOne<PrescriptionEntity>

    override fun convert(): SideEffect {
        return SideEffect(
            id,
            date,
            description,
            prescription.target?.convert()
        )
    }

    fun convertBacklink(): SideEffect {
        return SideEffect(
            id,
            date,
            description,
            prescription.target?.convertBacklink()
        )
    }
}
