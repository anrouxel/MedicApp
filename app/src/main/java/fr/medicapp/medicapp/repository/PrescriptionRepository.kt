package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.PrescriptionDAO
import fr.medicapp.medicapp.entity.PrescriptionEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités Prescription.
 *
 * @property prescriptionDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class PrescriptionRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val prescriptionDao: PrescriptionDAO
) {

    /**
     * Récupère toutes les prescriptions de la base de données.
     *
     * @return Une liste de toutes les entités Prescription dans la base de données.
     */
    fun getAll(): List<PrescriptionEntity> {
        return prescriptionDao.getAll()
    }

    /**
     * Récupère une prescription spécifique de la base de données.
     *
     * @param id L'identifiant unique de la prescription à récupérer.
     * @return L'entité Prescription correspondant à l'identifiant donné.
     */
    fun getOne(id: String): PrescriptionEntity {
        return prescriptionDao.getOne(id)
    }

    /**
     * Ajoute une nouvelle entité Prescription à la base de données.
     *
     * @param t L'entité Prescription à ajouter à la base de données.
     */
    fun add(t: PrescriptionEntity) {
        prescriptionDao.add(t)
    }

    /**
     * Supprime une entité Prescription spécifique de la base de données.
     *
     * @param t L'entité Prescription à supprimer de la base de données.
     */
    fun delete(t: PrescriptionEntity) {
        prescriptionDao.delete(t)
    }

    /**
     * Met à jour une entité Prescription spécifique dans la base de données.
     *
     * @param t L'entité Prescription à mettre à jour dans la base de données.
     */
    fun update(t: PrescriptionEntity) {
        prescriptionDao.update(t)
    }
}
