package fr.medicapp.medicapp.model

data class Alarm(
    val id: Long = 0L,

    var hour: Int = 0,

    var minute: Int = 0,

    var alarms: Int = 0
) {
    override fun toString(): String {
        return "${hour}h${minute}"
    }
}
