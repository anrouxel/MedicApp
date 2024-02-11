package fr.medicapp.medicapp.model

import androidx.compose.runtime.mutableStateListOf
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import java.time.LocalDate

data class Prescription(
    val id: Long = 0L,

    var date: LocalDate? = null,

    var doctor: Doctor? = null,

    var treatment: Treatment = Treatment(),

    var notifications: MutableList<Notification> = mutableStateListOf()
) : ModelToEntityMapper<PrescriptionEntity> {
    override fun convert(): PrescriptionEntity {
        val prescription = PrescriptionEntity(
            id,
            date
        )
        prescription.doctor.target = doctor?.convert()
        prescription.treatment.target = treatment.convert()
        prescription.notifications.addAll(notifications.map { it.convert() })
        return prescription
    }
}
