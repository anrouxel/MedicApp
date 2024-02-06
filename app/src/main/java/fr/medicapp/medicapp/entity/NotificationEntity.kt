package fr.medicapp.medicapp.entity

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import java.time.DayOfWeek

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
@Entity
data class NotificationEntity(

    /**
     * L'identifiant unique de la notification.
     */
    @Id
    var id: Long = 0L,

    /**
     * La fréquence de la notification.
     */
    var frequency: MutableList<DayOfWeek> = mutableListOf(),

    /**
     * Les heures de la notification.
     */
    var hours: MutableList<Int> = mutableListOf(),

    /**
     * Les minutes de la notification.
     */
    var minutes: MutableList<Int> = mutableListOf(),

    /**
     * Les alarmes de la notification.
     */
    var alarms: MutableList<Int> = mutableListOf(),


) {

    /**
     * Le traitement associé à la notification.
     */
    lateinit var medicationName: ToOne<TreatmentEntity>
}