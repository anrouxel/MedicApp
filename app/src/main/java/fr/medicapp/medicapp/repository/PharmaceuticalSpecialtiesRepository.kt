package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.PharmaceuticalSpecialtiesDAO
import fr.medicapp.medicapp.entity.PharmaceuticalSpecialtiesEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités PharmaceuticalSpecialties.
 *
 * @property pharmaceuticalSpecialtiesDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class PharmaceuticalSpecialtiesRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val pharmaceuticalSpecialtiesDao: PharmaceuticalSpecialtiesDAO,
) {

    /**
     * Récupère toutes les entités PharmaceuticalSpecialties de la base de données.
     *
     * @return Une liste de toutes les entités PharmaceuticalSpecialties dans la base de données.
     */
    fun getAll(): List<PharmaceuticalSpecialtiesEntity> {
        return pharmaceuticalSpecialtiesDao.getAll()
    }

    /**
     * Récupère une entité PharmaceuticalSpecialties spécifique de la base de données.
     *
     * @param id L'identifiant unique de l'entité PharmaceuticalSpecialties à récupérer.
     * @return L'entité PharmaceuticalSpecialties correspondant à l'identifiant donné.
     */
    fun getOne(id: String): PharmaceuticalSpecialtiesEntity {
        return pharmaceuticalSpecialtiesDao.getOne(id)
    }

    /**
     * Récupère une entité PharmaceuticalSpecialties associée à un médicament spécifique par son cisCode.
     *
     * @param cisCode Le cisCode du médicament pour lequel récupérer les spécialités pharmaceutiques.
     * @return Une liste d'entités PharmaceuticalSpecialties associée au médicament donné.
     */
    fun getByCisCode(cisCode: String): List<PharmaceuticalSpecialtiesEntity> {
        return pharmaceuticalSpecialtiesDao.getByCisCode(cisCode)
    }

    /**
     * Ajoute une nouvelle entité PharmaceuticalSpecialties à la base de données.
     *
     * @param t L'entité PharmaceuticalSpecialties à ajouter à la base de données.
     */
    fun add(t: PharmaceuticalSpecialtiesEntity) {
        pharmaceuticalSpecialtiesDao.add(t)
    }

    /**
     * Supprime une entité PharmaceuticalSpecialties spécifique de la base de données.
     *
     * @param t L'entité PharmaceuticalSpecialties à supprimer de la base de données.
     */
    fun delete(t: PharmaceuticalSpecialtiesEntity) {
        pharmaceuticalSpecialtiesDao.delete(t)
    }

    /**
     * Met à jour les informations d'une entité PharmaceuticalSpecialties spécifique dans la base de données.
     *
     * @param t L'entité PharmaceuticalSpecialties à mettre à jour dans la base de données.
     */
    fun update(t: PharmaceuticalSpecialtiesEntity) {
        pharmaceuticalSpecialtiesDao.update(t)
    }


}