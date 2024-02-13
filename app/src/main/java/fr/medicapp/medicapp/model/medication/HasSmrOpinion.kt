package fr.medicapp.medicapp.model.medication

import android.content.Context
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.HasSmrOpinionEntity
import java.time.LocalDate

data class HasSmrOpinion(
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var hasDossierCode: String = "",

    var evaluationReason: String = "",

    var transparencyCommissionOpinionDate: LocalDate? = null,

    var smrValue: String = "",

    var smrLabel: String = "",

    var transparencyCommissionOpinionLinks: MutableList<TransparencyCommissionOpinionLinks> = mutableListOf()
) : ModelToEntityMapper<HasSmrOpinionEntity> {
    override fun convert(context: Context): HasSmrOpinionEntity {
        val hasSmrOpinionEntity = HasSmrOpinionEntity(
            id,
            cisCode,
            hasDossierCode,
            evaluationReason,
            transparencyCommissionOpinionDate,
            smrValue,
            smrLabel
        )

        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(HasSmrOpinionEntity::class.java)
        store.attach(hasSmrOpinionEntity)

        hasSmrOpinionEntity.transparencyCommissionOpinionLinks.addAll(
            transparencyCommissionOpinionLinks.map { it.convert(context) }
        )
        return hasSmrOpinionEntity
    }
}