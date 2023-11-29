package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GenericType")
data class GenericType(
    @PrimaryKey val genericTypeInfoMap:String
)