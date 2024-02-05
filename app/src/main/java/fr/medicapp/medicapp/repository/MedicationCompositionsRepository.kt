package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.MedicationCompositionsDAO
import fr.medicapp.medicapp.entity.MedicationCompositionsEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités MedicationCompositions.
 *
 * @property medicationCompositionsDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class MedicationCompositionsRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val medicationCompositionsDao: MedicationCompositionsDAO,
) {

    /**
     * Récupère toutes les entités MedicationCompositions de la base de données.
     *
     * @return Une liste de toutes les entités MedicationCompositions dans la base de données.
     */
    fun getAll(): List<MedicationCompositionsEntity> {
        return medicationCompositionsDao.getAll()
    }

    /**
     * Récupère une entité MedicationCompositions spécifique de la base de données.
     *
     * @param id L'identifiant unique de l'entité MedicationCompositions à récupérer.
     * @return L'entité MedicationCompositions correspondant à l'identifiant donné.
     */
    fun getOne(id: String): MedicationCompositionsEntity {
        return medicationCompositionsDao.getOne(id)
    }

    /**
     * Récupère toutes les entités MedicationCompositions associées à un médicament spécifique.
     *
     * @param medicament Le médicament pour lequel récupérer les compositions.
     * @return Une entité MedicationCompositions associée au médicament donné.
     */
    fun getByCisCode(cisCode: String): List<MedicationCompositionsEntity> {
        return medicationCompositionsDao.getByCisCode(cisCode)
    }

    /**
     * Ajoute une nouvelle entité MedicationCompositions à la base de données.
     *
     * @param t L'entité MedicationCompositions à ajouter à la base de données.
     */
    fun add(t: MedicationCompositionsEntity) {
        medicationCompositionsDao.add(t)
    }

    /**
     * Supprime une entité MedicationCompositions spécifique de la base de données.
     *
     * @param t L'entité MedicationCompositions à supprimer de la base de données.
     */
    fun delete(t: MedicationCompositionsEntity) {
        medicationCompositionsDao.delete(t)
    }

    /**
     * Met à jour les informations d'une entité MedicationCompositions spécifique dans la base de données.
     *
     * @param t L'entité MedicationCompositions à mettre à jour dans la base de données.
     */
    fun update(t: MedicationCompositionsEntity) {
        medicationCompositionsDao.update(t)
    }


}