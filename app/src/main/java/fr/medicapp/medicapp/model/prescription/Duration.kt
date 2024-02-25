package fr.medicapp.medicapp.model.prescription

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Duration(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "duration_id")
    val id: Long = 0L,

    var startDate: LocalDate? = null,

    var endDate: LocalDate? = null
) {
    override fun toString(): String {
        return "Du $startDate au $endDate"
    }
}
