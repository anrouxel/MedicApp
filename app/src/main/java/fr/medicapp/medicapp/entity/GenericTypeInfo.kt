package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GenericTypeInfo")
data class GenericTypeInfo(
    @PrimaryKey val genericTypeId:Int,
    val genericTypeLabel:String
)