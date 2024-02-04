package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.TransparencyCommissionOpinionLinksEntity

/**
 * Interface DAO pour la gestion des données des liens des avis de la commission de transparence.
 *
 * Cette interface fournit des méthodes pour interagir avec la table TransparencyCommissionOpinionLinksEntity dans la base de données.
 */
@Dao
interface TransparencyCommissionOpinionLinksDAO {
    /**
     * Récupère tous les liens des avis de la commission de transparence de la base de données.
     *
     * @return Une liste de tous les liens des avis de la commission de transparence.
     */
    @Query("SELECT * FROM TransparencyCommissionOpinionLinks")
    fun getAll(): List<TransparencyCommissionOpinionLinksEntity>

    /**
     * Récupère un lien d'avis de la commission de transparence spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant du lien d'avis de la commission de transparence à récupérer.
     * @return L'entité du lien d'avis de la commission de transparence correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM TransparencyCommissionOpinionLinks WHERE id = :id")
    fun getOne(id: String): TransparencyCommissionOpinionLinksEntity

    /**
     * Ajoute un nouveau lien d'avis de la commission de transparence à la base de données.
     * @param t L'entité du lien d'avis de
     */
    @Insert
    fun add(t: TransparencyCommissionOpinionLinksEntity)

    /**
     * Supprime un lien d'avis de la commission de transparence spécifique de la base de données.
     * @param t L'entité du lien d'avis de la commission de transparence à supprimer.
     */
    @Delete
    fun delete(t: TransparencyCommissionOpinionLinksEntity)

    /**
     * Met à jour les informations d'un lien d'avis de la commission de transparence spécifique dans la base de données.
     * @param t L'entité du lien d'avis de
        */
    @Update
    fun update(t: TransparencyCommissionOpinionLinksEntity)
}