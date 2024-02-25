package fr.medicapp.medicapp.model.prescription.relationship

import androidx.compose.runtime.mutableStateListOf
import androidx.room.Embedded
import androidx.room.Relation
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.medication.MedicationInformation
import fr.medicapp.medicapp.model.medication.relationship.Medication
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.Duration
import fr.medicapp.medicapp.model.prescription.NotificationInformation
import fr.medicapp.medicapp.model.prescription.PrescriptionInformation
import fr.medicapp.medicapp.model.prescription.SideEffectInformation

data class Prescription(
    @Embedded val prescriptionInformation: PrescriptionInformation = PrescriptionInformation(),
    @Relation(
        parentColumn = "doctor_id",
        entityColumn = "doctor_id",
    )
    var doctor: Doctor? = null,

    @Relation(
        entity = MedicationInformation::class,
        parentColumn = "medication_id",
        entityColumn = "medication_id",
    )
    val medication: Medication? = null,

    @Relation(
        entity = Duration::class,
        parentColumn = "duration_id",
        entityColumn = "duration_id",
    )
    val duration: Duration? = null,

    @Relation(
        entity = NotificationInformation::class,
        parentColumn = "prescription_id",
        entityColumn = "notification_id",
    )
    val notifications: MutableList<Notification> = mutableStateListOf(),

    @Relation(
        entity = SideEffectInformation::class,
        parentColumn = "prescription_id",
        entityColumn = "side_effect_id",
    )
    val sideEffects: MutableList<SideEffectInformation> = mutableStateListOf()
) {
    override fun toString(): String {
        return medication!!.medicationInformation.name
    }

    fun toOptionDialog(): OptionDialog {
        return OptionDialog(
            id = prescriptionInformation.id,
            title = medication!!.medicationInformation.name,
            description = duration.toString(),
        )
    }
}
