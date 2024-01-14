package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.PrescriptionEntity

/**
 * Interface DAO pour la gestion des données des prescriptions.
 *
 * Cette interface fournit des méthodes pour interagir avec la table PrescriptionEntity dans la base de données.
 */
@Dao
interface PrescriptionDAO {

    /**
     * Récupère toutes les prescriptions de la base de données.
     *
     * @return Une liste de toutes les entités de prescriptions.
     */
    @Query("SELECT * FROM PrescriptionEntity")
    fun getAll(): List<PrescriptionEntity>

    /**
     * Récupère une prescription spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant de la prescription à récupérer.
     * @return L'entité de la prescription correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM PrescriptionEntity WHERE id = :id")
    fun getOne(id: String): PrescriptionEntity

    /**
     * Ajoute une nouvelle prescription à la base de données.
     *
     * @param t L'entité de la prescription à ajouter.
     */
    @Insert
    fun add(t: PrescriptionEntity)

    /**
     * Supprime une prescription spécifique de la base de données.
     *
     * @param t L'entité de la prescription à supprimer.
     */
    @Delete
    fun delete(t: PrescriptionEntity)

    /**
     * Met à jour les informations d'une prescription spécifique dans la base de données.
     *
     * @param t L'entité de la prescription à mettre à jour.
     */
    @Update
    fun update(t: PrescriptionEntity)
}