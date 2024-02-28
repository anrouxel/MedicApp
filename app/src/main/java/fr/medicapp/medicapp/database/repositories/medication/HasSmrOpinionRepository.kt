package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.database.repositories.medication.crossRef.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefRepository
import fr.medicapp.medicapp.model.medication.HasSmrOpinionInformation
import fr.medicapp.medicapp.model.medication.relationship.HasSmrOpinion
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef

class HasSmrOpinionRepository(context: Context) : Repository(context) {
    fun getAll(): List<HasSmrOpinionInformation> {
        return db.HasSmrOpinionDAO().getAll()
    }

    fun insert(hasSmrOpinion: HasSmrOpinion): Long {
        val id = db.HasSmrOpinionDAO().insert(hasSmrOpinion.hasSmrOpinionInformation)
        TransparencyCommissionOpinionLinksRepository(context).insert(hasSmrOpinion.transparencyCommissionOpinionLinks)
        hasSmrOpinion.transparencyCommissionOpinionLinks.forEach {
            HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefRepository(context).insert(
                HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef(
                    hasSmrOpinionId = id,
                    transparencyCommissionOpinionId = it.id
                )
            )
        }

        return id
    }

    fun insert(hasSmrOpinions: List<HasSmrOpinion>): List<Long> {
        val ids = mutableListOf<Long>()
        hasSmrOpinions.forEach {
            ids.add(insert(it))
        }
        return ids
    }

    fun delete(hasSmrOpinion: HasSmrOpinion) {
        hasSmrOpinion.transparencyCommissionOpinionLinks.forEach {
            HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefRepository(context).delete(
                HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef(
                    hasSmrOpinionId = hasSmrOpinion.hasSmrOpinionInformation.id,
                    transparencyCommissionOpinionId = it.id
                )
            )
        }
        TransparencyCommissionOpinionLinksRepository(context).delete(hasSmrOpinion.transparencyCommissionOpinionLinks)
        db.HasSmrOpinionDAO().delete(hasSmrOpinion.hasSmrOpinionInformation)
    }

    fun delete(hasSmrOpinions: List<HasSmrOpinion>) {
        hasSmrOpinions.forEach { delete(it) }
    }
}
