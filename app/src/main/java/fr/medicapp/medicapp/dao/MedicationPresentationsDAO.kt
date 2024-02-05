package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.MedicationPresentationsEntity

/**
 * Interface DAO pour la gestion des données des présentations de médicaments.
 *
 * Cette interface fournit des méthodes pour interagir avec la table MedicationPresentationsEntity dans la base de données.
 */
@Dao
interface MedicationPresentationsDAO {
    /**
     * Récupère toutes les présentations de médicaments de la base de données.
     *
     * @return Une liste de toutes les entités de présentations de médicaments.
     */
    @Query("SELECT * FROM MedicationPresentations")
    fun getAll(): List<MedicationPresentationsEntity>

    /**
     * Récupère une présentation de médicament spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant de la présentation de médicament à récupérer.
     * @return L'entité de la présentation de médicament correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM MedicationPresentations WHERE id = :id")
    fun getOne(id: String): MedicationPresentationsEntity

    /**
     * Récupère une présentation de médicament spécifique de la base de données par le code cis du médicament associé.
     *
     * @param cisCode Le code cis du médicament associé à la présentation de médicament à récupérer.
     * @return L'entité de la présentation de médicament correspondant au code cis donné.
     */
    @Query("SELECT * FROM MedicationPresentations WHERE cisCode = :cisCode")
    fun getByCisCode(cisCode: String): MedicationPresentationsEntity

    /**
     * Ajoute une nouvelle présentation de médicament à la base de données.
     * @param t La présentation de médicament à ajouter.
     */
    @Insert
    fun add(t: MedicationPresentationsEntity)

    /**
     * Supprime une présentation de médicament spécifique de la base de données.
     * @param t La présentation de médicament à supprimer.
     */
    @Delete
    fun delete(t: MedicationPresentationsEntity)

    /**
     * Met à jour les informations d'une présentation de médicament spécifique dans la base de données.
     * @param t La présentation de médicament à mettre à jour.
     */
    @Update
    fun update(t: MedicationPresentationsEntity)
}