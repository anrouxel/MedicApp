package fr.medicapp.medicapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import fr.medicapp.medicapp.entity.DurationEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Notification(
    var id : String = "",
    var medicationName : String = "",
    var frequency : String = "",
    var hours : MutableList<Int> = mutableStateListOf(),
    var minutes : MutableList<Int> = mutableStateListOf()
) {
}