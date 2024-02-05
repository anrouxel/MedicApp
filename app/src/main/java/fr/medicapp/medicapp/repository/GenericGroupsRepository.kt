package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.GenericGroupsDAO
import fr.medicapp.medicapp.entity.GenericGroupsEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités génériques.
 *
 * @property genericGroupsDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class GenericGroupsRepository (

        /**
        * L'objet DAO pour accéder aux méthodes de base de données.
        */
        private val genericGroupsDao: GenericGroupsDAO

) {

    /**
    * Récupère tous les groupes génériques de la base de données.
    *
    * @return Une liste de toutes les entités de groupes génériques dans la base de données.
    */
    fun getAll(): List<GenericGroupsEntity> {
        return genericGroupsDao.getAll()
    }

    /**
    * Récupère un groupe générique spécifique de la base de données.
    *
    * @param id L'identifiant unique du groupe générique à récupérer.
    * @return L'entité GenericGroups correspondant à l'identifiant donné.
    */
    fun getOne(id: String): GenericGroupsEntity {
        return genericGroupsDao.getOne(id)
    }

    /**
    * Ajoute une nouvelle entité GenericGroups à la base de données.
    *
    * @param t L'entité GenericGroups à ajouter à la base de données.
    */
    fun add(t: GenericGroupsEntity) {
        genericGroupsDao.add(t)
    }

    /**
    * Supprime une entité GenericGroups spécifique de la base de données.
    *
    * @param t L'entité GenericGroups à supprimer de la base de données.
    */
    fun delete(t: GenericGroupsEntity) {
        genericGroupsDao.delete(t)
    }

    /**
    * Met à jour les informations d'une entité GenericGroups spécifique dans la base de données.
    *
    * @param t L'entité GenericGroups à mettre à jour dans la base de données.
    */
    fun update(t: GenericGroupsEntity) {
        genericGroupsDao.update(t)
    }

}