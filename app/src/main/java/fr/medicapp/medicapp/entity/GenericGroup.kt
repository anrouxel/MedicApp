package fr.medicapp.medicapp.entity

import androidx.room.Entity

@Entity(tableName = "GenericGroup")
data class GenericGroup(
    val genericGroupId : String,
    val genericGroupLabel : String,
    val cisCode : String,
    val genericType : String,
    val sortNumber: Int?
)