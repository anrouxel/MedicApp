package fr.medicapp.medicapp.ui.notifications.NotificationsEdit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.toUpperCase
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun getFrenchDayOfWeek(dayOfWeek: DayOfWeek): String {
    return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.FRENCH).toUpperCase(Locale.FRENCH)
}