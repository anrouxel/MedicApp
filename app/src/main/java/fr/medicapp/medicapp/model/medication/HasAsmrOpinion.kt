package fr.medicapp.medicapp.model.medication

import android.content.Context
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.converter.ModelToEntityMapper
import fr.medicapp.medicapp.database.entity.medication.HasAsmrOpinionEntity
import java.time.LocalDate

data class HasAsmrOpinion(
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var hasDossierCode: String = "",

    var evaluationReason: String = "",

    var transparencyCommissionOpinionDate: LocalDate? = null,

    var asmrValue: String = "",

    var asmrLabel: String = "",

    var transparencyCommissionOpinionLinks: MutableList<TransparencyCommissionOpinionLinks> = mutableListOf()
) : ModelToEntityMapper<HasAsmrOpinionEntity> {
    override fun convert(context: Context): HasAsmrOpinionEntity {
        val hasAsmrOpinionEntity = HasAsmrOpinionEntity(
            id,
            cisCode,
            hasDossierCode,
            evaluationReason,
            transparencyCommissionOpinionDate,
            asmrValue,
            asmrLabel
        )

        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(HasAsmrOpinionEntity::class.java)
        store.attach(hasAsmrOpinionEntity)

        hasAsmrOpinionEntity.transparencyCommissionOpinionLinks.addAll(
            transparencyCommissionOpinionLinks.map { it.convert(context) }
        )
        return hasAsmrOpinionEntity
    }
}
