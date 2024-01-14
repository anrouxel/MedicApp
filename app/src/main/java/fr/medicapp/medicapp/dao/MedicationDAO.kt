package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.MedicationEntity

/**
 * Met à jour les informations d'un médecin spécifique dans la base de données.
 *
 * @param t L'entité du médecin à mettre à jour.
 */
@Dao
interface MedicationDAO {

    /**
     * Récupère tous les médicaments de la base de données.
     *
     * @return Une liste de toutes les entités de médicaments.
     */
    @Query("SELECT * FROM Medications")
    fun getAll(): List<MedicationEntity>

    /**
     * Récupère tous les médicaments de la base de données qui ne sont pas marqués comme 'NON COMMERCIALISÉE'.
     *
     * @return Une liste de toutes les entités de médicaments qui ne sont pas marqués comme 'NON COMMERCIALISÉE'.
     */
    @Query("SELECT * FROM Medications WHERE CommercializationStatus NOT LIKE 'NON COMMERCIALISÉE'")
    fun getAllWithoutNotTreadings(): List<MedicationEntity>

    /**
     * Récupère un médicament spécifique de la base de données par son code CIS.
     *
     * @param id Le code CIS du médicament à récupérer.
     * @return L'entité du médicament correspondant au code CIS donné.
     */
    @Query("SELECT * FROM Medications WHERE cisCode = :id")
    fun getOne(id: String): MedicationEntity

    /**
     * Ajoute un nouveau médicament à la base de données.
     *
     * @param t L'entité du médicament à ajouter.
     */
    @Insert
    fun add(t: MedicationEntity)

    /**
     * Supprime un médicament spécifique de la base de données.
     *
     * @param t L'entité du médicament à supprimer.
     */
    @Delete
    fun delete(t: MedicationEntity)

    /**
     * Met à jour les informations d'un médicament spécifique dans la base de données.
     *
     * @param t L'entité du médicament à mettre à jour.
     */
    @Update
    fun update(t: MedicationEntity)
}