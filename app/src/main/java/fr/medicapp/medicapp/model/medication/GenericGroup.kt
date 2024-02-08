package fr.medicapp.medicapp.entity.medication

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
)
