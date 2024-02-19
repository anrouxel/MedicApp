package fr.medicapp.medicapp.notification

import java.time.LocalDateTime

data class AlarmItem(
    val alarmTime: LocalDateTime,
    val message: String
)
