package fr.medicapp.medicapp.database.repositories.medication.crossRef

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef

class HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefRepository(context: Context) : Repository(context) {
    fun getAll(): List<HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef> {
        return db.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO().getAll()
    }

    fun insert(hasSmrOpinionTransparencyCommissionOpinionLinksCrossRef: HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef): Long {
        return db.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO().insert(
            hasSmrOpinionTransparencyCommissionOpinionLinksCrossRef
        )
    }

    fun insert(
        hasSmrOpinionTransparencyCommissionOpinionLinksCrossRef:
        List<HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef>
    ): List<Long> {
        return db.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO().insert(
            hasSmrOpinionTransparencyCommissionOpinionLinksCrossRef
        )
    }

    fun delete(hasSmrOpinionTransparencyCommissionOpinionLinksCrossRef: HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef) {
        db.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO().delete(
            hasSmrOpinionTransparencyCommissionOpinionLinksCrossRef
        )
    }
}
