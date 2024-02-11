package fr.medicapp.medicapp.model

import fr.medicapp.medicapp.entity.medication.Medication
import fr.medicapp.medicapp.database.entity.medication.MedicationEntity
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne

data class Treatment(
    var id: Long = 0L,

    var posology: String = "",

    var frequency: String = "",

    var medication: Medication? = null,

    var duration: Duration? = null
)
