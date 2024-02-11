package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.GenericGroupEntity
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

data class GenericGroup(
    val id: Long = 0L,

    var genericGroupId: Long = 0L,

    var genericGroupLabel: String = "",

    var cisCode: Long = 0L,

    var genericType: Int = 0,

    var genericName: String = "",

    var sortNumber: Int? = null
) : ModelToEntityMapper<GenericGroupEntity> {
    override fun convert(): GenericGroupEntity {
        return GenericGroupEntity(
            id,
            genericGroupId,
            genericGroupLabel,
            cisCode,
            genericType,
            genericName,
            sortNumber
        )
    }
}
