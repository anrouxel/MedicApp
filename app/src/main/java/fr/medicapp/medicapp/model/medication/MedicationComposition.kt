package fr.medicapp.medicapp.model.medication

import android.content.Context
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.MedicationCompositionEntity

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
    override fun convert(context: Context): MedicationCompositionEntity {
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