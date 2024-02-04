package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.MedicationCompositionsEntity

/**
 * Interface DAO pour la gestion des données des compositions de médicaments.
 *
 * Cette interface fournit des méthodes pour interagir avec la table MedicationCompositionsEntity dans la base de données.
 */
@Dao
interface MedicationCompositionsDAO {
    /**
     * Récupère toutes les compositions de médicaments de la base de données.
     *
     * @return Une liste de toutes les entités de compositions de médicaments.
     */
    @Query("SELECT * FROM MedicationCompositions")
    fun getAll(): List<MedicationCompositionsEntity>

    /**
     * Récupère une composition de médicament spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant de la composition de médicament à récupérer.
     * @return L'entité de la composition de médicament correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM MedicationCompositions WHERE id = :id")
    fun getOne(id: String): MedicationCompositionsEntity

    /**
     * Ajoute une nouvelle composition de médicament à la base de données.
     * @param t La composition de médicament à ajouter.
     */
    @Insert
    fun add(t: MedicationCompositionsEntity)

    /**
     * Supprime une composition de médicament spécifique de la base de données.
     * @param t La composition de médicament à supprimer.
     */
    @Delete
    fun delete(t: MedicationCompositionsEntity)

    /**
     * Met à jour les informations d'une composition de médicament spécifique dans la base de données.
     * @param t La composition de médicament à mettre à jour.
     */
    @Update
    fun update(t: MedicationCompositionsEntity)
}