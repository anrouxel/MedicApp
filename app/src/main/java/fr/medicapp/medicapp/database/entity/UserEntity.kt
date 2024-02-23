package fr.medicapp.medicapp.database.entity

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.LocalDateConverter
import fr.medicapp.medicapp.model.User
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.time.LocalDate

@Entity
data class UserEntity(
    @Id
    var id: Long = 0L,
    var lastName: String = "",
    var firstName: String = "",
    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var birthday: LocalDate = LocalDate.now(),
    var gender: String = "",
    var pregnant: Boolean = false,
    var allergies: MutableList<String>? = null
) : EntityToModelMapper<User> {
    override fun convert(): User {
        return User(
            id,
            lastName,
            firstName,
            birthday,
            gender,
            pregnant,
            allergies
        )
    }
}
