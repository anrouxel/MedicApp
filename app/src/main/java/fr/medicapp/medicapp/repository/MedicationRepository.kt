package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.MedicationDAO
import fr.medicapp.medicapp.entity.MedicationEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités Medication.
 *
 * @property medicationDAO L'objet DAO pour accéder aux méthodes de base de données.
 */
class MedicationRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val medicationDAO: MedicationDAO
) {

    /**
     * Récupère tous les médicaments de la base de données.
     *
     * @return Une liste de toutes les entités Medication dans la base de données.
     */
    fun getAll(): List<MedicationEntity> {
        return medicationDAO.getAll()
    }

    /**
     * Récupère tous les médicaments de la base de données sans les non-traités.
     *
     * @return Une liste de toutes les entités Medication dans la base de données sans les non-traités.
     */
    fun getAllWithoutNotTreadings(): List<MedicationEntity> {
        return medicationDAO.getAllWithoutNotTreadings()
    }

    /**
     * Récupère un médicament spécifique de la base de données.
     *
     * @param id L'identifiant unique du médicament à récupérer.
     * @return L'entité Medication correspondant à l'identifiant donné.
     */
    fun getOne(id: String): MedicationEntity {
        return medicationDAO.getOne(id)
    }

    /**
     * Ajoute une nouvelle entité Medication à la base de données.
     *
     * @param t L'entité Medication à ajouter à la base de données.
     */
    fun add(t: MedicationEntity) {
        medicationDAO.add(t)
    }

    /**
     * Supprime une entité Medication spécifique de la base de données.
     *
     * @param t L'entité Medication à supprimer de la base de données.
     */
    fun delete(t: MedicationEntity) {
        medicationDAO.delete(t)
    }

    /**
     * Met à jour une entité Medication spécifique dans la base de données.
     *
     * @param t L'entité Medication à mettre à jour dans la base de données.
     */
    fun update(t: MedicationEntity) {
        medicationDAO.update(t)
    }
}
