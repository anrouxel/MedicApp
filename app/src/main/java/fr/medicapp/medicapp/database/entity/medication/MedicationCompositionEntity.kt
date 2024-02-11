package fr.medicapp.medicapp.database.entity.medication

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.model.medication.MedicationComposition
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class MedicationCompositionEntity(
    @Id
    var id: Long = 0L,

    var cisCode: Long = 0L,

    var pharmaceuticalElementDesignation: String = "",

    var substanceCode: Long = 0L,

    var substanceName: String = "",

    var substanceDosage: String = "",

    var dosageReference: String = "",

    var componentNature: String = "",

    var linkNumber: Int? = null
) : EntityToModelMapper<MedicationComposition> {
    override fun convert(): MedicationComposition {
        return MedicationComposition(
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
