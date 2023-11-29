package fr.medicapp.medicapp.entity

import androidx.room.Entity

@Entity(tableName = "Label")
data class Label(
    val key: String
)