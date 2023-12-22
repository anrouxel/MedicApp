package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table nommée Label.
 * Elle est définie et stocke uniquement des key String.
 * */
@Entity(tableName = "Label")
data class Label(
    @PrimaryKey val key: String
)