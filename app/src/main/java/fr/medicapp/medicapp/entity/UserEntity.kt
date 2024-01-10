package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val lastName: String,

    val firstName: String,

    val age: Int,

    val email: String,
)
