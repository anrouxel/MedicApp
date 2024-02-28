package fr.medicapp.medicapp.notification

interface AlarmScheduler {
    fun schedule(alarmItem: AlarmItem)
    fun cancel(alarmItem: AlarmItem)
}
