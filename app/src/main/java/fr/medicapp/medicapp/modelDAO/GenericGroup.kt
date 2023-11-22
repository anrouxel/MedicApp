package fr.medicapp.medicapp.modelDAO

data class GenericGroup(
    val genericGroupId : String,
    val genericGroupLabel : String,
    val cisCode : String,
    val genericType : String,
    val sortNumber: Int?
)