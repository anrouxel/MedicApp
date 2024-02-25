package fr.medicapp.medicapp.database.repositories.medication

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.medication.TransparencyCommissionOpinionLinks

class TransparencyCommissionOpinionLinksRepository(context: Context) : Repository(context) {
    fun getAll(): List<TransparencyCommissionOpinionLinks> {
        return db.TransparencyCommissionOpinionLinksDAO().getAll()
    }

    fun insert(transparencyCommissionOpinionLinks: TransparencyCommissionOpinionLinks): Long {
        return db.TransparencyCommissionOpinionLinksDAO().insert(transparencyCommissionOpinionLinks)
    }

    fun insert(transparencyCommissionOpinionLinkss: List<TransparencyCommissionOpinionLinks>): List<Long> {
        return db.TransparencyCommissionOpinionLinksDAO()
            .insert(transparencyCommissionOpinionLinkss)
    }

    fun delete(transparencyCommissionOpinionLinks: TransparencyCommissionOpinionLinks) {
        db.TransparencyCommissionOpinionLinksDAO().delete(transparencyCommissionOpinionLinks)
    }

    fun delete(transparencyCommissionOpinionLinks: List<TransparencyCommissionOpinionLinks>) {
        transparencyCommissionOpinionLinks.forEach { delete(it) }
    }
}