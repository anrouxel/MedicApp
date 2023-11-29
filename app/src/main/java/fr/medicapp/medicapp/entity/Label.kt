package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Label")
data class Label(
    @PrimaryKey val key: String
)