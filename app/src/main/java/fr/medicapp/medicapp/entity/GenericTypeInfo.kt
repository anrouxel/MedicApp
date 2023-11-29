package fr.medicapp.medicapp.entity

import androidx.room.Entity

@Entity(tableName = "GenericTypeInfo")
data class GenericTypeInfo(
    val genericTypeId:Int,
    val genericTypeLabel:String
)