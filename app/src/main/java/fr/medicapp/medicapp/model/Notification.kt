package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.database.MutableListDayOfWeekConverter
import fr.medicapp.medicapp.database.MutableListIntConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import java.time.DayOfWeek

data class Notification(
    val id: Long = 0L,

    var frequency: MutableList<DayOfWeek> = mutableListOf(),

    var hours: MutableList<Int> = mutableListOf(),

    var minutes: MutableList<Int> = mutableListOf(),

    var alarms: MutableList<Int> = mutableListOf(),

    var treatment: Treatment? = null
)
