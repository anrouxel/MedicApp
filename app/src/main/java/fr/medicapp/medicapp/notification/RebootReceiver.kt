package fr.medicapp.medicapp.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.PrescriptionEntity

@RequiresApi(Build.VERSION_CODES.O)
class RebootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_LOCKED_BOOT_COMPLETED -> {
                val box = ObjectBox.getInstance(context)
                val store = box.boxFor(PrescriptionEntity::class.java)
                store.all.map { it.convert() }.forEach { prescription ->
                    prescription.notifications.forEach { notification ->
                        NotificationPrescriptionManager.add(
                            context,
                            notification,
                            "Time to take your medication",
                            "It's time to take your medication"
                        )
                    }
                }
            }

            else -> Toast.makeText(context, "Unknown action: ${intent.action}", Toast.LENGTH_SHORT)
                .show()
        }
    }
}