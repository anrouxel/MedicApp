package fr.medicapp.medicapp.entity

import fr.medicapp.medicapp.database.MutableListDayOfWeekConverter
import fr.medicapp.medicapp.database.MutableListIntConverter
import io.objectbox.annotation.Convert
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
    @Convert(converter = MutableListDayOfWeekConverter::class, dbType = String::class)
    var frequency: MutableList<DayOfWeek> = mutableListOf(),

    /**
     * Les heures de la notification.
     */
    @Convert(converter = MutableListIntConverter::class, dbType = String::class)
    var hours: MutableList<Int> = mutableListOf(),

    /**
     * Les minutes de la notification.
     */
    @Convert(converter = MutableListIntConverter::class, dbType = String::class)
    var minutes: MutableList<Int> = mutableListOf(),

    /**
     * Les alarmes de la notification.
     */
    @Convert(converter = MutableListIntConverter::class, dbType = String::class)
    var alarms: MutableList<Int> = mutableListOf(),


) {

    /**
     * Le traitement associé à la notification.
     */
    lateinit var treatment: ToOne<TreatmentEntity>
}