package fr.medicapp.medicapp.database.entity.medication

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.entity.medication.GenericGroup
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class GenericGroupEntity(
    @Id
    var id: Long = 0L,

    var genericGroupId: Long = 0L,

    var genericGroupLabel: String = "",

    var cisCode: Long = 0L,

    var genericType: Int = 0,

    var genericName: String = "",

    var sortNumber: Int? = null
) : EntityToModelMapper<GenericGroup> {
    override fun convert(): GenericGroup {
        return GenericGroup(
            id = id,
            genericGroupId = genericGroupId,
            genericGroupLabel = genericGroupLabel,
            cisCode = cisCode,
            genericType = genericType,
            genericName = genericName,
            sortNumber = sortNumber
        )
    }
}
