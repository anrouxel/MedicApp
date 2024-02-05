package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.MedicationPresentationsDAO
import fr.medicapp.medicapp.entity.MedicationPresentationsEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités MedicationPresentations.
 *
 * @property medicationPresentationsDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class MedicationPresentationsRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val medicationPresentationsDao: MedicationPresentationsDAO,
) {

    /**
     * Récupère toutes les entités MedicationPresentations de la base de données.
     *
     * @return Une liste de toutes les entités MedicationPresentations dans la base de données.
     */
    fun getAll(): List<MedicationPresentationsEntity> {
        return medicationPresentationsDao.getAll()
    }

    /**
     * Récupère une entité MedicationPresentations spécifique de la base de données.
     *
     * @param id L'identifiant unique de l'entité MedicationPresentations à récupérer.
     * @return L'entité MedicationPresentations correspondant à l'identifiant donné.
     */
    fun getOne(id: String): MedicationPresentationsEntity {
        return medicationPresentationsDao.getOne(id)
    }

    /**
     * Récupère toutes les entités MedicationPresentations associées à un médicament spécifique.
     *
     * @param cisCode Le cisCode du médicament pour lequel récupérer les présentations.
     * @return Une entité MedicationPresentations associée au médicament donné.
     */
    fun getByCisCode(cisCode: String): MedicationPresentationsEntity {
        return medicationPresentationsDao.getByCisCode(cisCode)
    }

    /**
     * Ajoute une nouvelle entité MedicationPresentations à la base de données.
     *
     * @param t L'entité MedicationPresentations à ajouter à la base de données.
     */
    fun add(t: MedicationPresentationsEntity) {
        medicationPresentationsDao.add(t)
    }

    /**
     * Supprime une entité MedicationPresentations spécifique de la base de données.
     *
     * @param t L'entité MedicationPresentations à supprimer de la base de données.
     */
    fun delete(t: MedicationPresentationsEntity) {
        medicationPresentationsDao.delete(t)
    }

    /**
     * Met à jour les informations d'une entité MedicationPresentations spécifique dans la base de données.
     *
     * @param t L'entité MedicationPresentations à mettre à jour dans la base de données.
     */
    fun update(t: MedicationPresentationsEntity) {
        medicationPresentationsDao.update(t)
    }




}