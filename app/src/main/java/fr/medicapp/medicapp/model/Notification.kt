package fr.medicapp.medicapp.model

import androidx.compose.runtime.mutableStateListOf
import java.time.DayOfWeek
import java.util.UUID

/**
 * Modèle représentant une notification.
 *
 * @property id L'identifiant unique de la notification.
 * @property medicationName Le traitement associé à la notification.
 * @property frequency La fréquence de la notification.
 * @property hours Les heures de la notification.
 * @property minutes Les minutes de la notification.
 * @property alarms Les alarmes de la notification.
 */
data class Notification(

    /**
     * L'identifiant unique de la notification.
     */
    var id: String = "",

    /**
     * Le traitement associé à la notification.
     */
    var medicationName: Treatment? = null,

    /**
     * La fréquence de la notification.
     */
    var frequency: MutableList<DayOfWeek> = mutableStateListOf(),

    /**
     * Les heures de la notification.
     */
    var hours: MutableList<Int> = mutableStateListOf(),

    /**
     * Les minutes de la notification.
     */
    var minutes: MutableList<Int> = mutableStateListOf(),

    /**
     * Les alarmes de la notification.
     */
    var alarms: MutableList<Int> = mutableStateListOf(),
)
