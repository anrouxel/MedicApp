package fr.medicapp.medicapp.notification

import java.time.LocalDateTime

data class AlarmItem(
    val id: Long,
    val alarmTime: LocalDateTime,
    val message: String
)
