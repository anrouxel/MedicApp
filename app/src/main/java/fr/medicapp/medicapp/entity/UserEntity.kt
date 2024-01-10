package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class UserEntity(
    @PrimaryKey
    val id: String,

    var lastName: String,

    var firstName: String,

    val age: Int,

    val email: String,
)
