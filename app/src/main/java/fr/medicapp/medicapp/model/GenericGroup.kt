package fr.medicapp.medicapp.model

data class GenericGroup(
    val genericGroupId : String,
    val genericGroupLabel : String,
    val cisCode : String,
    val genericType : String,
    val sortNumber: Int?
)