package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.PrescriptionDispensingConditionsDAO
import fr.medicapp.medicapp.entity.PrescriptionDispensingConditionsEntity


/**
 * Repository pour gérer les opérations de base de données pour les entités PrescriptionDispensingConditions.
 *
 * @property prescriptionDispensingConditionsDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class PrescriptionDispensingConditionsRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val prescriptionDispensingConditionsDao: PrescriptionDispensingConditionsDAO,
) {

    /**
     * Récupère toutes les entités PrescriptionDispensingConditions de la base de données.
     *
     * @return Une liste de toutes les entités PrescriptionDispensingConditions dans la base de données.
     */
    fun getAll(): List<PrescriptionDispensingConditionsEntity> {
        return prescriptionDispensingConditionsDao.getAll()
    }

    /**
     * Récupère une entité PrescriptionDispensingConditions spécifique de la base de données.
     *
     * @param id L'identifiant unique de l'entité PrescriptionDispensingConditions à récupérer.
     * @return L'entité PrescriptionDispensingConditions correspondant à l'identifiant donné.
     */
    fun getOne(id: String): PrescriptionDispensingConditionsEntity {
        return prescriptionDispensingConditionsDao.getOne(id)
    }

    /**
     * Ajoute une nouvelle entité PrescriptionDispensingConditions à la base de données.
     *
     * @param t L'entité PrescriptionDispensingConditions à ajouter à la base de données.
     */
    fun add(t: PrescriptionDispensingConditionsEntity) {
        prescriptionDispensingConditionsDao.add(t)
    }

    /**
     * Supprime une entité PrescriptionDispensingConditions spécifique de la base de données.
     *
     * @param t L'entité PrescriptionDispensingConditions à supprimer de la base de données.
     */
    fun delete(t: PrescriptionDispensingConditionsEntity) {
        prescriptionDispensingConditionsDao.delete(t)
    }

    /**
     * Met à jour les informations d'une entité PrescriptionDispensingConditions spécifique dans la base de données.
     *
     * @param t L'entité PrescriptionDispensingConditions à mettre à jour dans la base de données.
     */
    fun update(t: PrescriptionDispensingConditionsEntity) {
        prescriptionDispensingConditionsDao.update(t)
    }

}