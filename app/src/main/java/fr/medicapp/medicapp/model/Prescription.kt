package fr.medicapp.medicapp.model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.math.log

data class Prescription(
    val id: Long = 0L,

    var date: LocalDate? = null,

    var takes: MutableList<LocalDateTime> = mutableStateListOf(),

    var doctor: Doctor? = null,

    var treatment: Treatment = Treatment(),

    var notifications: MutableList<Notification> = mutableStateListOf()
) : ModelToEntityMapper<PrescriptionEntity> {
    override fun convert(context: Context): PrescriptionEntity {
        val prescription = PrescriptionEntity(
            id,
            date,
            takes
        )

        val box = ObjectBox.getInstance(context)
        val store = box.boxFor(PrescriptionEntity::class.java)
        store.attach(prescription)

        prescription.doctor.target = doctor?.convert(context)
        prescription.treatment.target = treatment.convert(context)
        prescription.notifications.clear()
        prescription.notifications.addAll(notifications.map { it.convert(context) })
        return prescription
    }
}
