package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.HasAsmrOpinionsEntity

/**
 * Interface DAO pour la gestion des données des avis ASMR.
 *
 * Cette interface fournit des méthodes pour interagir avec la table HasAsmrOpinionsEntity dans la base de données.
 */
@Dao
interface HasAsmrOpinionsDAO {
/**
     * Récupère tous les avis ASMR de la base de données.
     *
     * @return Une liste de toutes les entités d'avis ASMR.
     */
    @Query("SELECT * FROM HasAsmrOpinions")
    fun getAll(): List<HasAsmrOpinionsEntity>

    /**
     * Récupère un avis ASMR spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant de l'avis ASMR à récupérer.
     * @return L'entité de l'avis ASMR correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM HasAsmrOpinions WHERE id = :id")
    fun getOne(id: String): HasAsmrOpinionsEntity

    /**
     * Ajoute un nouvel avis ASMR à la base de données.
     * @param t L'entité de l'avis ASMR à ajouter.
     */
    @Insert
    fun add(t: HasAsmrOpinionsEntity)

    /**
     * Supprime un avis ASMR spécifique de la base de données.
     * @param t L'entité de l'avis ASMR à supprimer.
     */
    @Delete
    fun delete(t: HasAsmrOpinionsEntity)

    /**
     * Met à jour les informations d'un avis ASMR spécifique dans la base de données.
     * @param t L'entité de l'avis ASMR à mettre à jour.
     */
    @Update
    fun update(t: HasAsmrOpinionsEntity)

}