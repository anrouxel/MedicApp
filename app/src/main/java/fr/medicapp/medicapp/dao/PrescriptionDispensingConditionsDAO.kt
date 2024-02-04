package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.PrescriptionDispensingConditionsEntity

/**
 * Interface DAO pour la gestion des données des conditions de délivrance d'ordonnance.
 *
 * Cette interface fournit des méthodes pour interagir avec la table PrescriptionDispensingConditionsEntity dans la base de données.
 */
@Dao
interface PrescriptionDispensingConditionsDAO {
    /**
     * Récupère toutes les conditions de délivrance d'ordonnance de la base de données.
     *
     * @return Une liste de toutes les entités de conditions de délivrance d'ordonnance.
     */
    @Query("SELECT * FROM PrescriptionDispensingConditions")
    fun getAll(): List<PrescriptionDispensingConditionsEntity>

    /**
     * Récupère une condition de délivrance d'ordonnance spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant de la condition de délivrance d'ordonnance à récupérer.
     * @return L'entité de la condition de délivrance d'ordonnance correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM PrescriptionDispensingConditions WHERE id = :id")
    fun getOne(id: String): PrescriptionDispensingConditionsEntity

    /**
     * Ajoute une nouvelle condition de délivrance d'ordonnance à la base de données.
     * @param t La condition de délivrance d'ordonnance à ajouter.
     */
    @Insert
    fun add(t: PrescriptionDispensingConditionsEntity)

    /**
     * Supprime une condition de délivrance d'ordonnance spécifique de la base de données.
     * @param t La condition de délivrance d'ordonnance à supprimer.
     */
    @Delete
    fun delete(t: PrescriptionDispensingConditionsEntity)

    /**
     * Met à jour les informations d'une condition de délivrance d'ordonnance spécifique dans la base de données.
     *  @param t La condition de délivrance d'ordonnance à mettre à jour.
     */
    @Update
    fun update(t: PrescriptionDispensingConditionsEntity)
}