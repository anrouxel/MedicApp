package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.entity.DurationEntity
import fr.medicapp.medicapp.entity.MedicationEntity
import fr.medicapp.medicapp.entity.TreatmentEntity
import java.util.UUID

data class Treatment(
    var id: String = "",
    var medication: MedicationEntity? = null,
    var posology: String = "",
    var quantity: String = "",
    var renew: String = "",
    var duration: Duration? = null,
    var notification: Boolean = false,
    var query: String = ""
) {

    fun toEntity(): TreatmentEntity {
        return TreatmentEntity(
            id = UUID.randomUUID().toString(),
            medication = medication!!.cisCode,
            posology = posology,
            quantity = quantity,
            renew = renew,
            duration = duration?.toEntity()!!,
            notification = notification
        )
    }
}