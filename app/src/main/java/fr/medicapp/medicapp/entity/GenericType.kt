package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table nommée GenericType.
 * Ne permet que de stocker des String genericTypeInfoMap.
 * */
@Entity(tableName = "GenericType")
data class GenericType(
    @PrimaryKey val genericTypeInfoMap: String
)
