package fr.medicapp.medicapp.model

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import java.time.LocalDate

data class Prescription(
    val id: Long = 0L,

    var date: LocalDate? = null,

    var doctor: Doctor? = null,

    var treatment: Treatment = Treatment(),

    var notifications: MutableList<Notification> = mutableStateListOf(),

    var sideEffects: MutableList<SideEffect> = mutableStateListOf()
) : ModelToEntityMapper<PrescriptionEntity> {
    override fun convert(context: Context): PrescriptionEntity {
        val prescription = PrescriptionEntity(
            id,
            date
        )

        val box = ObjectBox.getInstance(context)
        val store = box.boxFor(PrescriptionEntity::class.java)
        store.attach(prescription)

        prescription.doctor.target = doctor?.convert(context)
        prescription.treatment.target = treatment.convert(context)
        prescription.notifications.clear()
        prescription.notifications.addAll(notifications.map { it.convert(context) })
        prescription.sideEffects.clear()
        prescription.sideEffects.addAll(sideEffects.map { it.convertBacklink(context) })
        return prescription
    }

    fun convertBacklink(context: Context): PrescriptionEntity {
        val prescription = PrescriptionEntity(
            id,
            date
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

    override fun toString(): String {
        return treatment.medication!!.name
    }

    fun toOptionDialog(): OptionDialog {
        return OptionDialog(
            id = id,
            title = treatment.medication!!.name,
            description = treatment.duration!!.toString(),
        )
    }
}
