package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.HasSmrOpinionsEntity
/**
 * Interface DAO pour la gestion des données des avis SMR.
 *
 * Cette interface fournit des méthodes pour interagir avec la table HasSmrOpinionsEntity dans la base de données.
 */
@Dao
interface HasSmrOpinionsDAO {
    /**
     * Récupère tous les avis SMR de la base de données.
     *
     * @return Une liste de toutes les entités d'avis SMR.
     */
    @Query("SELECT * FROM HasSmrOpinions")
    fun getAll(): List<HasSmrOpinionsEntity>

    /**
     * Récupère un avis SMR spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant de l'avis SMR à récupérer.
     * @return L'entité de l'avis SMR correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM HasSmrOpinions WHERE id = :id")
    fun getOne(id: String): HasSmrOpinionsEntity

    /**
     * Ajoute un nouvel avis SMR à la base de données.
     * @param t L'entité de l'avis SMR à ajouter.
     */
    @Insert
    fun add(t: HasSmrOpinionsEntity)

    /**
     * Supprime un avis SMR spécifique de la base de données.
     *  @param t L'entité de l'avis SMR à supprimer.
     */
    @Delete
    fun delete(t: HasSmrOpinionsEntity)

    /**
     * Met à jour les informations d'un avis SMR spécifique dans la base de données.
     * @param t L'entité de l'avis SMR à mettre à jour.
     */
    @Update
    fun update(t: HasSmrOpinionsEntity)
}