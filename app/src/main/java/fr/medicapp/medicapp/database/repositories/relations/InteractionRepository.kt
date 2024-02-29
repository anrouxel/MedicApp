package fr.medicapp.medicapp.database.repositories.relations

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.relations.Interactions

class InteractionRepository(contexte: Context) : Repository(contexte) {
    fun getAll(): List<Interactions> {
        return db.InteractionsDAO().getAll()
    }

    fun insert(interactions: Interactions): Long {
        return db.InteractionsDAO().insert(interactions)
    }

    fun insert(interactions: List<Interactions>): List<Long> {
        return db.InteractionsDAO().insert(interactions)
    }

    fun delete(interactions: Interactions) {
        db.InteractionsDAO().delete(interactions)
    }
}