package fr.medicapp.medicapp.entity

import java.util.Calendar
import java.util.Date

data class Frequency(
    var hour: Int? = null,
    var day: Int? = null,
) {
    /*init {
        require(hour in 0..23) { "Heure comprise entre 0 et 23" }
        require(day in 1..7) { "Jour de la semaine compris entre 0 et 6" }
    }*/
}
