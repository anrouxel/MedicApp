package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.TreatmentEntity

/**
 * Interface DAO pour la gestion des données des traitements.
 *
 * Cette interface fournit des méthodes pour interagir avec la table TreatmentEntity dans la base de données.
 */
@Dao
interface TreatmentDAO {

    /**
     * Récupère tous les traitements de la base de données.
     *
     * @return Une liste de toutes les entités de traitements.
     */
    @Query("SELECT * FROM TreatmentEntity")
    fun getAll(): List<TreatmentEntity>

    /**
     * Récupère un traitement spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant du traitement à récupérer.
     * @return L'entité du traitement correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM TreatmentEntity WHERE id = :id")
    fun getOne(id: String): TreatmentEntity

    /**
     * Récupère tous les traitements de la base de données qui ont une notification.
     *
     * @return Une liste de toutes les entités de traitements qui ont une notification.
     */
    @Query("SELECT * FROM TreatmentEntity WHERE notification = 1")
    fun getWithNotification(): List<TreatmentEntity>

    /**
     * Ajoute un nouveau traitement à la base de données.
     *
     * @param t L'entité du traitement à ajouter.
     */
    @Insert
    fun add(t: TreatmentEntity)

    /**
     * Supprime un traitement spécifique de la base de données.
     *
     * @param t L'entité du traitement à supprimer.
     */
    @Delete
    fun delete(t: TreatmentEntity)

    /**
     * Met à jour les informations d'un traitement spécifique dans la base de données.
     *
     * @param t L'entité du traitement à mettre à jour.
     */
    @Update
    fun update(t: TreatmentEntity)
}