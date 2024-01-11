package fr.medicapp.medicapp.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maxkeppeler.sheets.option.models.Option

@Entity
data class TreatmentEntity(
    @PrimaryKey
    val id: String,

    var medication: String,

    var posology: String,

    var quantity: String,

    var renew: String,

    @Embedded
    val duration: DurationEntity,

    var notification: Boolean = false
) {
    fun toOption(): Option {
        return Option(
            titleText = medication,
            subtitleText = posology,
        )
    }
}
