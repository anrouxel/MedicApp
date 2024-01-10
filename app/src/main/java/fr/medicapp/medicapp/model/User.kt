package fr.medicapp.medicapp.model

data class User(
    var id: String = "",

    val lastName: String,

    val firstName: String,

    val age: Int,

    val email: String,
)