package fr.medicapp.medicapp.notification

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import de.coldtea.smplr.smplralarm.alarmNotification
import de.coldtea.smplr.smplralarm.channel
import de.coldtea.smplr.smplralarm.smplrAlarmCancel
import de.coldtea.smplr.smplralarm.smplrAlarmSet
import de.coldtea.smplr.smplralarm.smplrAlarmUpdate
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.Prescription
import java.time.DayOfWeek

@RequiresApi(Build.VERSION_CODES.O)
object NotificationPrescriptionManager {
    fun add(context: Context, notification: Notification, message: String, bigText: String) {
        notification.alarms.forEach { alarm ->
            smplrAlarmSet(context) {
                requestCode { notification.id.toInt() }
                isActive { notification.active }
                hour { alarm.hour }
                min { alarm.minute }
                weekdays {
                    notification.days.forEach { dayOfWeek ->
                        when (dayOfWeek) {
                            DayOfWeek.MONDAY -> monday()
                            DayOfWeek.TUESDAY -> tuesday()
                            DayOfWeek.WEDNESDAY -> wednesday()
                            DayOfWeek.THURSDAY -> thursday()
                            DayOfWeek.FRIDAY -> friday()
                            DayOfWeek.SATURDAY -> saturday()
                            DayOfWeek.SUNDAY -> sunday()
                        }
                    }
                }
                notification {
                    alarmNotification {
                        smallIcon { R.drawable.medicapp_eu_blue }
                        title { "Rappel de prise de médicament" }
                        message { message }
                        bigText { bigText }
                        autoCancel { false }
                    }
                }
                notificationChannel {
                    channel {
                        importance { NotificationManager.IMPORTANCE_HIGH }
                        showBadge { true }
                        name { "Canal de rappel de médicaments" }
                        description { "Ce canal de notification est utilisé pour les rappels" }
                    }
                }
            }
        }
    }

    fun remove(context: Context, notification: Notification) {
        smplrAlarmCancel(context) {
            requestCode { notification.id.toInt() }
        }
    }

    fun update(context: Context, notification: Notification) {
        smplrAlarmUpdate(context) {
            requestCode { notification.id.toInt() }
            isActive { notification.active }
        }
    }
}