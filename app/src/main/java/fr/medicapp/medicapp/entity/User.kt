package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Table nommée User représentant un User (Patient).
 * Utilise une MutableMap de Medication.
 * */
@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true) val id: UUID,
    val lastName: String,
    val firstName: String,
    val age: Int,
    val email: String,
    val mdp: String,
    val medicationMap: MutableMap<String, Medication>
)
