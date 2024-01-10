package fr.medicapp.medicapp.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

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
)
