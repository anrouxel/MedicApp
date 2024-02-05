package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.TransparencyCommissionOpinionLinksDAO
import fr.medicapp.medicapp.entity.TransparencyCommissionOpinionLinksEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités TransparencyCommissionOpinionLinks.
 *
 * @property transparencyCommissionOpinionLinksDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class TransparencyCommissionOpinionLinksRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val transparencyCommissionOpinionLinksDao: TransparencyCommissionOpinionLinksDAO,
) {

    /**
     * Récupère toutes les entités TransparencyCommissionOpinionLinks de la base de données.
     *
     * @return Une liste de toutes les entités TransparencyCommissionOpinionLinks dans la base de données.
     */
    fun getAll(): List<TransparencyCommissionOpinionLinksEntity> {
        return transparencyCommissionOpinionLinksDao.getAll()
    }

    /**
     * Récupère une entité TransparencyCommissionOpinionLinks spécifique de la base de données.
     *
     * @param id L'identifiant unique de l'entité TransparencyCommissionOpinionLinks à récupérer.
     * @return L'entité TransparencyCommissionOpinionLinks correspondant à l'identifiant donné.
     */
    fun getOne(id: String): TransparencyCommissionOpinionLinksEntity {
        return transparencyCommissionOpinionLinksDao.getOne(id)
    }

    /**
     * Ajoute une nouvelle entité TransparencyCommissionOpinionLinks à la base de données.
     *
     * @param t L'entité TransparencyCommissionOpinionLinks à ajouter à la base de données.
     */
    fun add(t: TransparencyCommissionOpinionLinksEntity) {
        transparencyCommissionOpinionLinksDao.add(t)
    }

    /**
     * Supprime une entité TransparencyCommissionOpinionLinks spécifique de la base de données.
     *
     * @param t L'entité TransparencyCommissionOpinionLinks à supprimer de la base de données.
     */
    fun delete(t: TransparencyCommissionOpinionLinksEntity) {
        transparencyCommissionOpinionLinksDao.delete(t)
    }

    /**
     * Met à jour les informations d'une entité TransparencyCommissionOpinionLinks spécifique dans la base de données.
     *
     * @param t L'entité TransparencyCommissionOpinionLinks à mettre à jour dans la base de données.
     */
    fun update(t: TransparencyCommissionOpinionLinksEntity) {
        transparencyCommissionOpinionLinksDao.update(t)
    }
}