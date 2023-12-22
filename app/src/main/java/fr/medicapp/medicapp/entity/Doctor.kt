package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table nommé Doctor représentant un utilisateur médecin.
 * Le Doctor possède un id unique. Nous connaissons son nom, prénom, age et email.
 * Son mot de passe (mdp) doit être encodé.
 * La liste de User qui lui est associé devrait permettre au Doctor de suivre ses patients.
 * */
@Entity(tableName = "Doctor")
data class Doctor(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val lastName: String,
    val firstName: String,
    val age: Int,
    val email: String,
    val mdp: ByteArray,
    val patientList: List<User>
)
