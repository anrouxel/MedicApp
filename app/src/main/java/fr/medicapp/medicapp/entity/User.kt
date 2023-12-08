package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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