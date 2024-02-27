package fr.medicapp.medicapp.model.prescription.relationship

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
    var medication: Medication? = null,

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

    fun LocalDate.isInRange(startDate: LocalDate, endDate: LocalDate): Boolean {
        return this.isAfter(startDate.minusDays(1)) && this.isBefore(endDate.plusDays(1))
    }

    fun getNotificationsDates(date: LocalDate): MutableList<Take> {
        val notifications = mutableListOf<Take>()

        val startDate = duration!!.startDate!!
        val endDate = duration.endDate!!

        this.notifications
            .filter { notification ->
                notification.notificationInformation.days
                    .any { day ->
                        date.dayOfWeek.value == day.value && date.isInRange(
                            startDate,
                            endDate
                        )
                    }
            }
            .flatMap { notification ->
                notification.alarms.map { alarm ->
                    val hour = alarm.time.hour
                    val minute = alarm.time.minute
                    val dateTime =
                        LocalDateTime.of(date.year, date.month, date.dayOfMonth, hour, minute)
                    Take(alarm.id, this, dateTime)
                }
            }
            .toCollection(notifications)

        notifications.sortBy { it.date }

        return notifications
    }

    fun LocalDate.isInRange(endDate: LocalDate): Boolean {
        return this.isBefore(endDate)
    }

    fun getAlarmsForNotification(
        notification: Notification,
        startDate: LocalDate = LocalDateTime.now().toLocalDate()
    ): MutableList<AlarmItem> {
        val alarms = mutableListOf<AlarmItem>()
        var date = startDate

        while (date.isInRange(duration!!.endDate!!)) {
            if (notification.notificationInformation.days.any { it.value == date.dayOfWeek.value }) {
                notification.alarms.forEach { alarm ->
                    val hour = alarm.time.hour
                    val minute = alarm.time.minute
                    val dateTime =
                        LocalDateTime.of(date.year, date.month, date.dayOfMonth, hour, minute)

                    if (dateTime.isAfter(LocalDateTime.now())) {
                        alarms.add(
                            AlarmItem(
                                alarm.id,
                                dateTime,
                                "C'est l'heure de prendre votre médicament : ${medication!!.medicationInformation.name}"
                            )
                        )
                    }
                }
            }
            date = date.plusDays(1)
        }

        return alarms
    }

    fun getNextAlarms(alarmId: Long): AlarmItem? {
        return this.notifications.flatMap { getAlarmsForNotification(it) }
            .firstOrNull { it.id == alarmId }
    }

    fun getNextAlarms(): MutableList<AlarmItem> {
        return this.notifications.flatMap { getAlarmsForNotification(it) }
            .sortedBy { it.alarmTime }
            .toMutableList()
    }

    fun getNextNotificationAlarms(notificationId: Long): List<AlarmItem> {
        return this.notifications.find { it.notificationInformation.id == notificationId }
            ?.let { getAlarmsForNotification(it) }
            .orEmpty()
    }

    fun getNotificationAlarms(notificationId: Long): MutableList<AlarmItem> {
        return this.notifications.find { it.notificationInformation.id == notificationId }
            ?.let { notification ->
                notification.alarms.mapNotNull { alarm ->
                    getNextAlarms(alarm.id)
                }
            }
            .orEmpty()
            .toMutableList()
    }

    fun getAllAlarms(): MutableList<AlarmItem> {
        return this.notifications.flatMap { getAlarmsForNotification(it) }
            .toMutableList()
    }
}
