package fr.medicapp.medicapp.entity

data class GenericGroupEntity(
    var genericGroupId: Long,
    var genericGroupLabel: String,
    var cisCode: Long,
    var genericType: Int,
    var genericName: String,
    var sortNumber: Int?
)
