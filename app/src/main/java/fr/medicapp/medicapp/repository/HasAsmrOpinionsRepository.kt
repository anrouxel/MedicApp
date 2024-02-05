package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.HasAsmrOpinionsDAO
import fr.medicapp.medicapp.entity.HasAsmrOpinionsEntity


/**
 * Repository pour gérer les opérations de base de données pour les entités HasAsmrOpinions.
 *
 * @property hasAsmrOpinionsDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class HasAsmrOpinionsRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val hasAsmrOpinionsDao: HasAsmrOpinionsDAO,
) {

    /**
     * Récupère toutes les entités HasAsmrOpinions de la base de données.
     *
     * @return Une liste de toutes les entités HasAsmrOpinions dans la base de données.
     */
    fun getAll(): List<HasAsmrOpinionsEntity> {
        return hasAsmrOpinionsDao.getAll()
    }

    /**
     * Récupère une entité HasAsmrOpinions spécifique de la base de données.
     *
     * @param id L'identifiant unique de l'entité HasAsmrOpinions à récupérer.
     * @return L'entité HasAsmrOpinions correspondant à l'identifiant donné.
     */
    fun getOne(id: String): HasAsmrOpinionsEntity {
        return hasAsmrOpinionsDao.getOne(id)
    }

    /**
     * Récupère toutes les entités HasAsmrOpinions de la base de données correspondant à un code CIS.
     *
     * @param cisCode Le code CIS du médicament.
     * @return Une liste de toutes les entités HasAsmrOpinions correspondant au code CIS donné.
     */
    fun getByCisCode(cisCode: String): List<HasAsmrOpinionsEntity> {
        return hasAsmrOpinionsDao.getByCisCode(cisCode)
    }

    /**
     * Ajoute une nouvelle entité HasAsmrOpinions à la base de données.
     *
     * @param t L'entité HasAsmrOpinions à ajouter à la base de données.
     */
    fun add(t: HasAsmrOpinionsEntity) {
        hasAsmrOpinionsDao.add(t)
    }

    /**
     * Supprime une entité HasAsmrOpinions spécifique de la base de données.
     *
     * @param t L'entité HasAsmrOpinions à supprimer de la base de données.
     */
    fun delete(t: HasAsmrOpinionsEntity) {
        hasAsmrOpinionsDao.delete(t)
    }

    /**
     * Met à jour les informations d'une entité HasAsmrOpinions spécifique dans la base de données.
     *
     * @param t L'entité HasAsmrOpinions à mettre à jour dans la base de données.
     */
    fun update(t: HasAsmrOpinionsEntity) {
        hasAsmrOpinionsDao.update(t)
    }
}