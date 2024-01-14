package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.DoctorDAO
import fr.medicapp.medicapp.entity.DoctorEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités Doctor.
 *
 * @property doctorDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class DoctorRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val doctorDao: DoctorDAO
) {

    /**
     * Récupère tous les docteurs de la base de données.
     *
     * @return Une liste de toutes les entités Doctor dans la base de données.
     */
    fun getAll(): List<DoctorEntity> {
        return doctorDao.getAll()
    }

    /**
     * Récupère un docteur spécifique de la base de données.
     *
     * @param id L'identifiant unique du docteur à récupérer.
     * @return L'entité Doctor correspondant à l'identifiant donné.
     */
    fun getOne(id: String): DoctorEntity {
        return doctorDao.getOne(id)
    }

    /**
     * Ajoute une nouvelle entité Doctor à la base de données.
     *
     * @param t L'entité Doctor à ajouter à la base de données.
     */
    fun add(t: DoctorEntity) {
        doctorDao.add(t)
    }

    /**
     * Supprime une entité Doctor spécifique de la base de données.
     *
     * @param t L'entité Doctor à supprimer de la base de données.
     */
    fun delete(t: DoctorEntity) {
        doctorDao.delete(t)
    }

    /**
     * Met à jour une entité Doctor spécifique dans la base de données.
     *
     * @param t L'entité Doctor à mettre à jour dans la base de données.
     */
    fun update(t: DoctorEntity) {
        doctorDao.update(t)
    }
}
