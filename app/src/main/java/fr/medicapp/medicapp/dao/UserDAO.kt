package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.UserEntity

/**
 * Interface DAO pour la gestion des données des utilisateurs.
 *
 * Cette interface fournit des méthodes pour interagir avec la table UserEntity dans la base de données.
 */
@Dao
interface UserDAO {

    /**
     * Récupère tous les utilisateurs de la base de données.
     *
     * @return Une liste de toutes les entités d'utilisateurs.
     */
    @Query("SELECT * FROM UserEntity")
    fun getAll(): List<UserEntity>

    /**
     * Récupère un utilisateur spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant de l'utilisateur à récupérer.
     * @return L'entité de l'utilisateur correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM UserEntity WHERE id = :id")
    fun getOne(id: String): UserEntity

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     *
     * @param t L'entité de l'utilisateur à ajouter.
     */
    @Insert
    fun add(t: UserEntity)

    /**
     * Supprime un utilisateur spécifique de la base de données.
     *
     * @param t L'entité de l'utilisateur à supprimer.
     */
    @Delete
    fun delete(t: UserEntity)

    /**
     * Met à jour les informations d'un utilisateur spécifique dans la base de données.
     *
     * @param t L'entité de l'utilisateur à mettre à jour.
     */
    @Update
    fun update(t: UserEntity)
}