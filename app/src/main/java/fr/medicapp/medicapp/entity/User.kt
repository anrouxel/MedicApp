package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table nommée User représentant un User (Patient).
 * Utilise une MutableMap de Medication.
 * */
@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val lastName: String,
    val firstName: String,
    val age: Int,
    val email: String,
    val mdp: ByteArray,
    val medicationMap: MutableMap<String,Medication>
)