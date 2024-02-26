package fr.medicapp.medicapp.database.repositories.medication.crossRef

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef

class HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefRepository(context: Context) :
    Repository(context) {
    fun getAll(): List<HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef> {
        return db.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO().getAll()
    }

    fun insert(hasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef: HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef): Long {
        return db.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO().insert(
            hasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef
        )
    }

    fun insert(
        hasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef:
        List<HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef>
    ): List<Long> {
        return db.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO().insert(
            hasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef
        )
    }

    fun delete(hasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef: HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef) {
        db.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO().delete(
            hasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef
        )
    }
}
