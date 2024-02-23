package fr.medicapp.medicapp.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Prescription
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

    fun add(context: Context, alarms: List<AlarmItem>) {
        val alarmScheduler = getInstances(context)

        alarms.forEach {
            it.let(alarmScheduler::schedule)
        }
    }

    fun remove(context: Context, alarms: List<AlarmItem>) {
        val alarmScheduler = getInstances(context)
        alarms.forEach {
            it.let(alarmScheduler::cancel)
        }
    }
}
