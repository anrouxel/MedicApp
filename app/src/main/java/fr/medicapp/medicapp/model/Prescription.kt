package fr.medicapp.medicapp.model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import fr.medicapp.medicapp.notification.AlarmItem
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

    fun getNotificationsDates(date: LocalDate): MutableList<Take> {
        val notifications = mutableListOf<Take>()

        val startDate = treatment.duration!!.startDate!!
        val endDate = treatment.duration!!.endDate!!

        this.notifications.forEach { notification ->
            notification.days.forEach { day ->
                val dayOfWeek = day.value
                if (date.dayOfWeek.value == dayOfWeek &&
                    date.isAfter(startDate.minusDays(1)) &&
                    date.isBefore(endDate.plusDays(1))
                ) {
                    notification.alarms.forEach { alarm ->
                        val hour = alarm.hour
                        val minute = alarm.minute
                        val dateTime =
                            LocalDateTime.of(
                                date.year,
                                date.month,
                                date.dayOfMonth,
                                hour,
                                minute
                            )
                        notifications.add(Take(alarm.id, this, dateTime))
                    }
                }
            }
        }

        notifications.sortBy { it.date }

        return notifications
    }

    fun getNextAlarms(): MutableList<AlarmItem> {
        val alarms = mutableListOf<AlarmItem>()
        val now = LocalDateTime.now()

        Log.d("Alarm", "Now: $now")

        Log.d("Alarm", this.notifications.count().toString())

        this.notifications.forEach { notification ->
            var date = now.toLocalDate()

            Log.d("Alarm", "Date: $date")
            Log.d("Alarm", "End: ${treatment.duration!!.endDate}")
            Log.d("Alarm", "After: ${date.isAfter(treatment.duration!!.endDate)}")
            Log.d("Alarm", "Before: ${date.isBefore(treatment.duration!!.endDate)}")

            while (date.isBefore(treatment.duration!!.endDate)) {
                notification.alarms.forEach { alarm ->
                    if (alarms.none { it.id == alarm.id }) {
                        val hour = alarm.hour
                        val minute = alarm.minute
                        val dateTime = LocalDateTime.of(
                            date.year,
                            date.month,
                            date.dayOfMonth,
                            hour,
                            minute
                        )

                        Log.d("Alarm", "Alarm: $dateTime")
                        Log.d("Alarm", "Now: $now")
                        Log.d("Alarm", "Before: ${dateTime.isBefore(now)}")
                        Log.d("Alarm", "After: ${dateTime.isAfter(now)}")

                        if (dateTime.isAfter(now)) {
                            Log.d("Alarm", "Alarm: $dateTime")
                            alarms.add(AlarmItem(alarm.id, dateTime, ""))
                        }
                    }
                }
                date = date.plusDays(1)
            }
        }

        return alarms
    }

    fun getNextNotificationAlarms(notificationId: Long): MutableList<AlarmItem> {
        val alarms = mutableListOf<AlarmItem>()
        val now = LocalDateTime.now()

        this.notifications.find { it.id == notificationId }?.let { notification ->
            val date = now.toLocalDate()
            while (date.isAfter(treatment.duration!!.endDate)) {
                if (notification.days.any { it.value == date.dayOfWeek.value }) {
                    notification.alarms.forEach { alarm ->
                        if (alarms.none { it.id == alarm.id }) {
                            val hour = alarm.hour
                            val minute = alarm.minute
                            val dateTime = LocalDateTime.of(
                                date.year,
                                date.month,
                                date.dayOfMonth,
                                hour,
                                minute
                            )
                            if (dateTime.isBefore(now)) {
                                alarms.add(AlarmItem(alarm.id, dateTime, ""))
                            }
                        }
                    }
                }
                date.plusDays(1)
            }
        }

        return alarms
    }

    fun getNotificationAlarms(notificationId: Long): MutableList<AlarmItem> {
        val alarms = mutableListOf<AlarmItem>()

        this.notifications.find { it.id == notificationId }?.let { notification ->
            notification.alarms.forEach { alarm ->
                alarms.add(AlarmItem(alarm.id, LocalDateTime.now(), ""))
            }
        }

        return alarms
    }

    fun getAllAlarms(): MutableList<AlarmItem> {
        val alarms = mutableListOf<AlarmItem>()

        this.notifications.forEach { notification ->
            notification.alarms.forEach { alarm ->
                alarms.add(AlarmItem(alarm.id, LocalDateTime.now(), ""))
            }
        }

        return alarms
    }
}
