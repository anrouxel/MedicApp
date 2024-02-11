package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.MedicationCompositionEntity
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

data class MedicationComposition(
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var pharmaceuticalElementDesignation: String = "",

    var substanceCode: Long = 0L,

    var substanceName: String = "",

    var substanceDosage: String = "",

    var dosageReference: String = "",

    var componentNature: String = "",

    var linkNumber: Int? = null
) : ModelToEntityMapper<MedicationCompositionEntity> {
    override fun convert(): MedicationCompositionEntity {
        return MedicationCompositionEntity(
            id,
            cisCode,
            pharmaceuticalElementDesignation,
            substanceCode,
            substanceName,
            substanceDosage,
            dosageReference,
            componentNature,
            linkNumber
        )
    }
}
