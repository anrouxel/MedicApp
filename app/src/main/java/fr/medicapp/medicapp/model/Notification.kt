package fr.medicapp.medicapp.model

import androidx.compose.runtime.mutableStateListOf
import java.time.DayOfWeek

data class Notification(
    val id: Long = 0L,

    var active: Boolean = false,

    var days: MutableList<DayOfWeek> = mutableStateListOf(),

    var alarms: MutableList<Alarm> = mutableStateListOf()
)
