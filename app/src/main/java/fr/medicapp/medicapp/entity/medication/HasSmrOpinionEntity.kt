package fr.medicapp.medicapp.entity.medication

import fr.medicapp.medicapp.database.LocalDateConverter
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
) {
    var transparencyCommissionOpinionLinks: MutableList<TransparencyCommissionOpinionLinksEntity> = ToMany(this,
        HasSmrOpinionEntity_.transparencyCommissionOpinionLinks
    )
}
