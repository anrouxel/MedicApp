package fr.medicapp.medicapp.model

import java.time.DayOfWeek

data class Notification(
    val id: Long = 0L,

    var enabled: Boolean = false,

    var frequency: MutableList<DayOfWeek> = mutableListOf(),

    var alarms: MutableList<Alarm> = mutableListOf()
)
