package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.DoctorEntity

data class Doctor(
    val id: Long = 0L,

    var rpps: Long = 0L,

    var name: String = "",
) : ModelToEntityMapper<DoctorEntity> {
    override fun convert(): DoctorEntity {
        return DoctorEntity(
            id,
            rpps,
            name
        )
    }
}
