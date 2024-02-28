package fr.medicapp.medicapp.notification

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import fr.medicapp.medicapp.R
import fr.medicapp.medicapp.database.repositories.prescription.PrescriptionRepository

@RequiresApi(Build.VERSION_CODES.O)
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        Thread {
            val message = intent.getStringExtra("EXTRA_MESSAGE")
            val id = intent.getLongExtra("EXTRA_ID", 0)
            val channelId = NotificationPrescriptionManager.channelId
            context?.let { ctx ->
                val notificationManager =
                    ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val builder = NotificationCompat.Builder(ctx, channelId)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("MedicApp")
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                notificationManager.notify(1, builder.build())
            }

            val alarmItem = PrescriptionRepository(context!!).getByAlarmId(id).getNextAlarms(id)
            if (alarmItem != null) {
                val alarmScheduler = AlarmSchedulerImpl(context)
                alarmScheduler.schedule(alarmItem)
            }
        }.start()
    }
}
