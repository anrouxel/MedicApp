package fr.medicapp.medicapp.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import fr.medicapp.medicapp.model.Notification
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
object NotificationPrescriptionManager {
    private var INSTANCE: AlarmScheduler? = null

    val channelId = "prescription_notification"
    val channelName = "prescription_notification"

    fun getInstances(context: Context): AlarmScheduler {
        return INSTANCE ?: synchronized(this) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)

            val instance = AlarmSchedulerImpl(context)
            INSTANCE = instance
            instance
        }
    }

    fun add(context: Context, notification: Notification, message: String, bigText: String) {
        val alarmScheduler = getInstances(context)
        notification.alarms.forEach { alarm ->
            val alarmItem = AlarmItem(
                id = alarm.id,
                alarmTime = LocalTime.of(alarm.hour, alarm.minute).atDate(LocalDate.now()),
                message = message
            )
            alarmItem.let(alarmScheduler::schedule)
        }
    }

    fun remove(context: Context, notification: Notification) {
        val alarmScheduler = getInstances(context)
        notification.alarms.forEach { alarm ->
            val alarmItem = AlarmItem(
                id = alarm.id,
                alarmTime = LocalTime.of(alarm.hour, alarm.minute).atDate(LocalDate.now()),
                message = ""
            )
            alarmItem.let(alarmScheduler::cancel)
        }
    }

    fun update(context: Context, notification: Notification, message: String, bigText: String) {
        if (notification.active) {
            add(context, notification, message, bigText)
        } else {
            remove(context, notification)
        }
    }
}
