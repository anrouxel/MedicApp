package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Table nommé Doctor représentant un utilisateur médecin.
 * Le Doctor possède un id unique. Nous connaissons son nom, prénom, age et email.
 * Son mot de passe (mdp) doit être encodé.
 * La liste de User qui lui est associé devrait permettre au Doctor de suivre ses patients.
 * */
@Entity(tableName = "Doctor")
data class Doctor(
    @PrimaryKey(autoGenerate = true) val id: UUID,
    val lastName: String,
    val firstName: String,
)
