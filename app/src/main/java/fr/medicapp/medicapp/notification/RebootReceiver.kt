package fr.medicapp.medicapp.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
class RebootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_BOOT_COMPLETED,
            Intent.ACTION_LOCKED_BOOT_COMPLETED -> {
                NotificationPrescriptionManager.add(context, emptyList())
            }

            else -> Toast.makeText(context, "Unknown action: ${intent.action}", Toast.LENGTH_SHORT)
                .show()
        }
    }
}