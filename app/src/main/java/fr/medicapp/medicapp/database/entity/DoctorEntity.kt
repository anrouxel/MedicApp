package fr.medicapp.medicapp.database.entity

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.model.Doctor
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class DoctorEntity(
    @Id
    var id: Long = 0L,

    var rpps: Long = 0L,

    var name: String = "",
) : EntityToModelMapper<Doctor> {
    override fun convert(): Doctor {
        return Doctor(
            id,
            rpps,
            name
        )
    }
}

