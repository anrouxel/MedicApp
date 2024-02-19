package fr.medicapp.medicapp.model

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import java.time.LocalDate
import java.time.LocalDateTime

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

    fun getNotificationsBetweenDates(startDate: LocalDate, endDate: LocalDate): MutableList<Take> {
        val notifications = mutableListOf<Take>()

        val duration = Duration(
            startDate = startDate,
            endDate = endDate,
        )

        if (treatment.duration != null && treatment.duration!!.startDate != null) {
            if (treatment.duration!!.startDate!!.isAfter(startDate)) {
                duration.startDate = treatment.duration!!.startDate
            }
            if (treatment.duration!!.endDate != null && treatment.duration!!.endDate!!.isBefore(
                    endDate
                )
            ) {
                duration.endDate = treatment.duration!!.endDate
            }
        }

        this.notifications.forEach { notification ->
            notification.days.forEach { day ->
                val dayOfWeek = day.value
                var date = duration.startDate!!
                while (date.isBefore(duration.endDate)) {
                    if (date.dayOfWeek.value == dayOfWeek) {
                        notification.alarms.forEach { alarm ->
                            val hour = alarm.hour
                            val minute = alarm.minute
                            val dateTime = LocalDateTime.of(
                                date.year,
                                date.month,
                                date.dayOfMonth,
                                hour,
                                minute
                            )
                            notifications.add(Take(this, dateTime))
                        }
                    }
                    date = date.plusDays(1)
                }
            }
        }

        notifications.sortBy { it.date }

        return notifications
    }

    fun getNotificationsDates(date: LocalDate): MutableList<Take> {
        val notifications = mutableListOf<Take>()

        val startDate = treatment.duration!!.startDate!!
        val endDate = treatment.duration!!.endDate!!

//        if (treatment.duration != null && treatment.duration!!.startDate != null) {
//        }

        this.notifications.forEach { notification ->
            notification.days.forEach { day ->
                val dayOfWeek = day.value
                if (date.dayOfWeek.value == dayOfWeek
                    && date.isAfter(startDate.minusDays(1))
                    && date.isBefore(endDate.plusDays(1))) {
                    notification.alarms.forEach { alarm ->
                        val hour = alarm.hour
                        val minute = alarm.minute
                        val dateTime =
                            LocalDateTime.of(date.year, date.month, date.dayOfMonth, hour, minute)
                        notifications.add(Take(this, dateTime))
                    }
                }
            }
        }

        notifications.sortBy { it.date }

        return notifications
    }
}
