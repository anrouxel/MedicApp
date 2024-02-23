package fr.medicapp.medicapp.model

import android.content.Context
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.UserEntity
import java.time.LocalDate

data class User(
    var id: Long = 0L,
    val lastName: String = "",
    val firstName: String = "",
    val birthday: LocalDate = LocalDate.now(),
    val gender: String = "",
    val pregnant: Boolean = false,
    val allergies: MutableList<String> = mutableListOf()
) : ModelToEntityMapper<UserEntity> {
    override fun convert(context: Context): UserEntity {
        val user = UserEntity(
            id,
            lastName,
            firstName,
            birthday,
            gender,
            pregnant,
            allergies
        )

        return user

    }
}
