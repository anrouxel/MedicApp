package fr.medicapp.medicapp.model.prescription.relationship

import androidx.room.Embedded
import androidx.room.Relation
import fr.medicapp.medicapp.model.prescription.PrescriptionInformation
import fr.medicapp.medicapp.model.prescription.SideEffectInformation

data class SideEffect(
    @Embedded
    val sideEffectInformation: SideEffectInformation = SideEffectInformation(),

    @Relation(
        entity = PrescriptionInformation::class,
        parentColumn = "prescription_id",
        entityColumn = "prescription_id"
    )
    val prescription: Prescription? = null
)
