package fr.medicapp.medicapp.model.prescription.relationship

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.room.Embedded
import androidx.room.Relation
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.Take
import fr.medicapp.medicapp.model.medication.MedicationInformation
import fr.medicapp.medicapp.model.medication.relationship.Medication
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.Duration
import fr.medicapp.medicapp.model.prescription.NotificationInformation
import fr.medicapp.medicapp.model.prescription.PrescriptionInformation
import fr.medicapp.medicapp.model.prescription.SideEffectInformation
import fr.medicapp.medicapp.notification.AlarmItem
import java.time.LocalDate
import java.time.LocalDateTime

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

    fun getNotificationsDates(date: LocalDate): MutableList<Take> {
        val notifications = mutableListOf<Take>()

        val startDate = duration!!.startDate!!
        val endDate = duration.endDate!!

        this.notifications.forEach { notification ->
            notification.notificationInformation.days.forEach { day ->
                val dayOfWeek = day.value
                if (date.dayOfWeek.value == dayOfWeek &&
                    date.isAfter(startDate.minusDays(1)) &&
                    date.isBefore(endDate.plusDays(1))
                ) {
                    notification.alarms.forEach { alarm ->
                        val hour = alarm.time!!.hour
                        val minute = alarm.time.minute
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
            Log.d("Alarm", "End: ${duration!!.endDate}")
            Log.d("Alarm", "After: ${date.isAfter(duration.endDate)}")
            Log.d("Alarm", "Before: ${date.isBefore(duration.endDate)}")

            while (date.isBefore(duration.endDate)) {
                notification.alarms.forEach { alarm ->
                    if (alarms.none { it.id == alarm.id }) {
                        val hour = alarm.time!!.hour
                        val minute = alarm.time.minute
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

        this.notifications.find { it.notificationInformation.id == notificationId }?.let { notification ->
            val date = now.toLocalDate()
            while (date.isAfter(duration!!.endDate)) {
                if (notification.notificationInformation.days.any { it.value == date.dayOfWeek.value }) {
                    notification.alarms.forEach { alarm ->
                        if (alarms.none { it.id == alarm.id }) {
                            val hour = alarm.time!!.hour
                            val minute = alarm.time.minute
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

        this.notifications.find { it.notificationInformation.id == notificationId }?.let { notification ->
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
