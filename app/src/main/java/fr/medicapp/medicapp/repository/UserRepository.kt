package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.UserDAO
import fr.medicapp.medicapp.entity.UserEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités User.
 *
 * @property userDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class UserRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val userDao: UserDAO
) {

    /**
     * Récupère tous les utilisateurs de la base de données.
     *
     * @return Une liste de toutes les entités User dans la base de données.
     */
    fun getAll(): List<UserEntity> {
        return userDao.getAll()
    }

    /**
     * Récupère un utilisateur spécifique de la base de données.
     *
     * @param id L'identifiant unique de l'utilisateur à récupérer.
     * @return L'entité User correspondant à l'identifiant donné.
     */
    fun getOne(id: String): UserEntity {
        return userDao.getOne(id)
    }

    /**
     * Ajoute une nouvelle entité User à la base de données.
     *
     * @param t L'entité User à ajouter à la base de données.
     */
    fun add(t: UserEntity) {
        userDao.add(t)
    }

    /**
     * Supprime une entité User spécifique de la base de données.
     *
     * @param t L'entité User à supprimer de la base de données.
     */
    fun delete(t: UserEntity) {
        userDao.delete(t)
    }

    /**
     * Met à jour une entité User spécifique dans la base de données.
     *
     * @param t L'entité User à mettre à jour dans la base de données.
     */
    fun update(t: UserEntity) {
        userDao.update(t)
    }
}
