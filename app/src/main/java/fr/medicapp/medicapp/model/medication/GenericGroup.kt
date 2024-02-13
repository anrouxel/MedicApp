package fr.medicapp.medicapp.model.medication

import android.content.Context
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.GenericGroupEntity

data class GenericGroup(
    val id: Long = 0L,

    var genericGroupId: Long = 0L,

    var genericGroupLabel: String = "",

    var cisCode: Long = 0L,

    var genericType: Int = 0,

    var genericName: String = "",

    var sortNumber: Int? = null
) : ModelToEntityMapper<GenericGroupEntity> {
    override fun convert(context: Context): GenericGroupEntity {
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