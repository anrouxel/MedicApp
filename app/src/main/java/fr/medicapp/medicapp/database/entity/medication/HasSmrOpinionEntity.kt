package fr.medicapp.medicapp.database.entity.medication

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.database.converter.LocalDateConverter
import fr.medicapp.medicapp.model.medication.HasSmrOpinion
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany
import java.time.LocalDate

@Entity
data class HasSmrOpinionEntity(
    @Id
    var id: Long = 0L,

    var cisCode: Long = 0L,

    var hasDossierCode: String = "",

    var evaluationReason: String = "",

    @Convert(converter = LocalDateConverter::class, dbType = String::class)
    var transparencyCommissionOpinionDate: LocalDate? = null,

    var smrValue: String = "",

    var smrLabel: String = "",
) : EntityToModelMapper<HasSmrOpinion> {
    var transparencyCommissionOpinionLinks: MutableList<TransparencyCommissionOpinionLinksEntity> = ToMany(
        this,
        HasSmrOpinionEntity_.transparencyCommissionOpinionLinks
    )

    override fun convert(): HasSmrOpinion {
        return HasSmrOpinion(
            id,
            cisCode,
            hasDossierCode,
            evaluationReason,
            transparencyCommissionOpinionDate,
            smrValue,
            smrLabel,
            transparencyCommissionOpinionLinks.map { it.convert() }.toMutableList()
        )
    }
}
