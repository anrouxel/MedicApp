package fr.medicapp.medicapp.entity

import androidx.room.Entity

@Entity(tableName = "User")
data class User(
    val id:Int,
    val lastName: String,
    val firstName: String,
    val age: Int,
    val email: String,
    val mdp: String,
    val medicationMap: MutableMap<String,Medication>
)