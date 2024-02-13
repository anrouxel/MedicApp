package fr.medicapp.medicapp.database.entity.medication

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.LocalDateConverter
import fr.medicapp.medicapp.model.medication.HasAsmrOpinion
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import java.time.LocalDate

@Entity
data class HasAsmrOpinionEntity(
    @Id
    var id: Long = 0L,

    var cisCode: Long = 0L,

    var hasDossierCode: String = "",

    var evaluationReason: String = "",

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var transparencyCommissionOpinionDate: LocalDate? = null,

    var asmrValue: String = "",

    var asmrLabel: String = "",
) : EntityToModelMapper<HasAsmrOpinion> {
    var transparencyCommissionOpinionLinks: MutableList<TransparencyCommissionOpinionLinksEntity> = ToMany(
        this,
        HasAsmrOpinionEntity_.transparencyCommissionOpinionLinks
    )

    override fun convert(): HasAsmrOpinion {
        return HasAsmrOpinion(
            id,
            cisCode,
            hasDossierCode,
            evaluationReason,
            transparencyCommissionOpinionDate,
            asmrValue,
            asmrLabel,
            transparencyCommissionOpinionLinks.map { it.convert() }.toMutableList()
        )
    }
}