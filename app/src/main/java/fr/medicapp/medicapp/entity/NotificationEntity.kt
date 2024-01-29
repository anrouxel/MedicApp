package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.medicapp.medicapp.model.Notification
import java.time.DayOfWeek

/**
 * Entité représentant une notification dans la base de données.
 *
 * @property id L'identifiant unique de la notification.
 * @property medicationName Le nom du médicament associé à la notification.
 * @property frequency La fréquence de la notification, représentée par une liste de jours de la semaine.
 * @property hours Les heures auxquelles la notification doit être déclenchée.
 * @property minutes Les minutes auxquelles la notification doit être déclenchée.
 * @property alarms Les identifiants des alarmes associées à la notification.
 */
@Entity
data class NotificationEntity(

    /**
     * L'identifiant unique de la notification.
     */
    @PrimaryKey
    val id: String,

    /**
     * Le nom du médicament associé à la notification.
     */
    val medicationName: String,

    /**
     * La fréquence de la notification, représentée par une liste de jours de la semaine.
     */
    val frequency: MutableList<DayOfWeek>,

    /**
     * Les heures auxquelles la notification doit être déclenchée.
     */
    val hours: MutableList<Int>,

    /**
     * Les minutes auxquelles la notification doit être déclenchée.
     */
    val minutes: MutableList<Int>,

    /**
     * Les identifiants des alarmes associées à la notification.
     */
    val alarms: MutableList<Int>
) {

    /**
     * Convertit cette entité en un objet Notification.
     *
     * @return Un objet Notification correspondant à cette entité.
     */
    fun toNotification(): Notification {
        return Notification(
            id = id,
            medicationName = null,
            frequency = frequency.sortedBy{it.ordinal}.toMutableList(),
            hours = hours,
            minutes = minutes,
            alarms = alarms
        )
    }
}