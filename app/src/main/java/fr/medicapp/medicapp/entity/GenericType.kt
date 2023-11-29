package fr.medicapp.medicapp.entity

import androidx.room.Entity

@Entity(tableName = "GenericType")
data class GenericType(
    val genericTypeInfoMap:String
)