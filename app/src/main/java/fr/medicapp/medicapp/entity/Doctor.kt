package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Doctor")
data class Doctor(@PrimaryKey(autoGenerate = true) val id:Int,
                  val lastName: String,
                  val firstName: String,
                  val age: Int,
                  val email: String,
                  val mdp: String,
                  val patientList: List<User>)