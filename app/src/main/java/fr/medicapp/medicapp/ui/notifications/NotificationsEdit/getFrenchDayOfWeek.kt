package fr.medicapp.medicapp.ui.notifications.NotificationsEdit

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

/**
 * Cette fonction convertit un jour de la semaine en français.
 *
 * @param dayOfWeek Le jour de la semaine à convertir.
 * @return Le nom du jour de la semaine en français.
 * @throws IllegalArgumentException si le jour de la semaine n'est pas valide.
 */
@RequiresApi(Build.VERSION_CODES.O)
fun getFrenchDayOfWeek(dayOfWeek: DayOfWeek): String {
    return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.FRENCH).toUpperCase(Locale.FRENCH)
}