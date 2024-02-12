package fr.medicapp.medicapp.model

import android.content.Context
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.DoctorEntity

data class Doctor(
    val id: Long = 0L,

    var rpps: Long = 0L,

    var name: String = "",
) : ModelToEntityMapper<DoctorEntity> {
    override fun convert(context: Context): DoctorEntity {
        return DoctorEntity(
            id,
            rpps,
            name
        )
    }
}
