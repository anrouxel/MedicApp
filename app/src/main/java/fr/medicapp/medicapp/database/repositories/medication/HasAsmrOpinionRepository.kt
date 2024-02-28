package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.database.repositories.medication.crossRef.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefRepository
import fr.medicapp.medicapp.model.medication.HasAsmrOpinionInformation
import fr.medicapp.medicapp.model.medication.relationship.HasAsmrOpinion
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef

class HasAsmrOpinionRepository(context: Context) : Repository(context) {
    fun getAll(): List<HasAsmrOpinionInformation> {
        return db.HasAsmrOpinionDAO().getAll()
    }

    fun insert(hasAsmrOpinion: HasAsmrOpinion): Long {
        val id = db.HasAsmrOpinionDAO().insert(hasAsmrOpinion.hasAsmrOpinionInformation)
        TransparencyCommissionOpinionLinksRepository(context).insert(hasAsmrOpinion.transparencyCommissionOpinionLinks)
        hasAsmrOpinion.transparencyCommissionOpinionLinks.forEach {
            HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefRepository(context).insert(
                HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef(
                    hasAsmrOpinionId = id,
                    transparencyCommissionOpinionId = it.id
                )
            )
        }

        return id
    }

    fun insert(hasAsmrOpinions: List<HasAsmrOpinion>): List<Long> {
        val ids = mutableListOf<Long>()
        hasAsmrOpinions.forEach {
            ids.add(insert(it))
        }
        return ids
    }

    fun delete(hasAsmrOpinion: HasAsmrOpinion) {
        hasAsmrOpinion.transparencyCommissionOpinionLinks.forEach {
            HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefRepository(context).delete(
                HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef(
                    hasAsmrOpinionId = hasAsmrOpinion.hasAsmrOpinionInformation.id,
                    transparencyCommissionOpinionId = it.id
                )
            )
        }
        TransparencyCommissionOpinionLinksRepository(context).delete(hasAsmrOpinion.transparencyCommissionOpinionLinks)
        db.HasAsmrOpinionDAO().delete(hasAsmrOpinion.hasAsmrOpinionInformation)
    }

    fun delete(hasAsmrOpinions: List<HasAsmrOpinion>) {
        hasAsmrOpinions.forEach { delete(it) }
    }
}
