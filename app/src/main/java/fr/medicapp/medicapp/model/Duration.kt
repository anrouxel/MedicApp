package fr.medicapp.medicapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import fr.medicapp.medicapp.entity.DurationEntity
import java.time.LocalDate

data class Duration(
    var startDate: LocalDate,

    var endDate: LocalDate
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun isValide(): Boolean {
        return startDate.isBefore(endDate)
    }

    override fun toString(): String {
        return "$startDate - $endDate"
    }

    fun toEntity(): DurationEntity {
        return DurationEntity(
            startDate = startDate,
            endDate = endDate
        )
    }
}