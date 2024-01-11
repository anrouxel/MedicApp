package fr.medicapp.medicapp.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.Treatment
import fr.medicapp.medicapp.repository.MedicationRepository

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
    fun toOptionDialog(): OptionDialog {
        return OptionDialog(
            id = id,
            title = medication,
            description = posology,
        )
    }

    fun toTreatment(repositoryMedication: MedicationRepository): Treatment {
        return Treatment(
            id = id,
            medication = repositoryMedication.getOne(medication),
            posology = posology,
            quantity = quantity,
            renew = renew,
            duration = duration.toDuration(),
            notification = notification
        )
    }
}
