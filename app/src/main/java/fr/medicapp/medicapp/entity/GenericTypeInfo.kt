package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table nommée GenericTypeInfo.
 * Définie par un genericTypeId.
 * */
@Entity(tableName = "GenericTypeInfo")
data class GenericTypeInfo(
    @PrimaryKey val genericTypeId:Int,
    val genericTypeLabel:String
)