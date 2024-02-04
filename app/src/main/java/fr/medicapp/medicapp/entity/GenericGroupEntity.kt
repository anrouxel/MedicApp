package fr.medicapp.medicapp.entity

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
)
