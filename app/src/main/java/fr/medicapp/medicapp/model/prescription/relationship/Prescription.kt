package fr.medicapp.medicapp.model.prescription.relationship

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import fr.medicapp.medicapp.model.medication.MedicationInformation
import fr.medicapp.medicapp.model.medication.relationship.Medication
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.Duration
import fr.medicapp.medicapp.model.prescription.NotificationInformation
import fr.medicapp.medicapp.model.prescription.PrescriptionInformation
import fr.medicapp.medicapp.model.prescription.SideEffect

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
    val duration: Duration = Duration(),

    @Relation(
        entity = NotificationInformation::class,
        parentColumn = "prescription_id",
        entityColumn = "notification_id",
    )
    val notifications: List<Notification> = emptyList(),

    @Relation(
        entity = SideEffect::class,
        parentColumn = "prescription_id",
        entityColumn = "side_effect_id",
    )
    val sideEffects: List<SideEffect> = emptyList()
)
