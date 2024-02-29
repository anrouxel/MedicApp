package fr.medicapp.medicapp.database.repositories.relations

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.relations.crossRef.Relations

class RelationRepository(contexte: Context) : Repository(contexte) {
    fun getAll(): List<Relations> {
        return db.RelationsDAO().getAll()
    }

    fun insert(relation: Relations): Long {
        val id = db.RelationsDAO().insert(relation.relationInfo)

        relation.interactions.forEach {
            it.relationInfoId = id
        }

        return id
    }

    fun insert(relations: List<Relations>): List<Long> {
        relations.forEach {
            insert(it)
        }
        return relations.map { it.relationInfo.id }
    }

    fun delete(relation: Relations) {
        relation.interactions.forEach {
            db.InteractionsDAO().delete(it)
        }
        db.RelationsDAO().delete(relation.relationInfo)
    }
}