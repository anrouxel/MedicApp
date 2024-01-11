package fr.medicapp.medicapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import fr.medicapp.medicapp.entity.DurationEntity
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Duration(
    var startDate: LocalDate,

    var endDate: LocalDate
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun isValide(): Boolean {
        return startDate.isBefore(endDate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun toString(): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return "${startDate.format(formatter)} - ${endDate.format(formatter)}"
    }

    fun toEntity(): DurationEntity {
        return DurationEntity(
            startDate = startDate,
            endDate = endDate
        )
    }
}