package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.TreatmentDAO
import fr.medicapp.medicapp.entity.TreatmentEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités Treatment.
 *
 * @property treatmentDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class TreatmentRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val treatmentDao: TreatmentDAO
) {

    /**
     * Récupère tous les traitements de la base de données.
     *
     * @return Une liste de toutes les entités Treatment dans la base de données.
     */
    fun getAll(): List<TreatmentEntity> {
        return treatmentDao.getAll()
    }

    /**
     * Récupère un traitement spécifique de la base de données.
     *
     * @param id L'identifiant unique du traitement à récupérer.
     * @return L'entité Treatment correspondant à l'identifiant donné.
     */
    fun getOne(id: String): TreatmentEntity {
        return treatmentDao.getOne(id)
    }

    /**
     * Récupère tous les traitements avec une notification.
     *
     * @return Une liste de toutes les entités Treatment avec une notification.
     */
    fun getWithNotification(): List<TreatmentEntity> {
        return treatmentDao.getWithNotification()
    }

    /**
     * Ajoute une nouvelle entité Treatment à la base de données.
     *
     * @param t L'entité Treatment à ajouter à la base de données.
     */
    fun add(t: TreatmentEntity) {
        treatmentDao.add(t)
    }

    /**
     * Supprime une entité Treatment spécifique de la base de données.
     *
     * @param t L'entité Treatment à supprimer de la base de données.
     */
    fun delete(t: TreatmentEntity) {
        treatmentDao.delete(t)
    }

    /**
     * Met à jour une entité Treatment spécifique dans la base de données.
     *
     * @param t L'entité Treatment à mettre à jour dans la base de données.
     */
    fun update(t: TreatmentEntity) {
        treatmentDao.update(t)
    }
}
