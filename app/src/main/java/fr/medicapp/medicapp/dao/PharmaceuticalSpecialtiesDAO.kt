package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.PharmaceuticalSpecialtiesEntity

/**
 * Interface DAO pour la gestion des données des spécialités pharmaceutiques.
 *
 * Cette interface fournit des méthodes pour interagir avec la table PharmaceuticalSpecialtiesEntity dans la base de données.
 */
@Dao
interface PharmaceuticalSpecialtiesDAO {
    /**
     * Récupère toutes les spécialités pharmaceutiques de la base de données.
     *
     * @return Une liste de toutes les entités de spécialités pharmaceutiques.
     */
    @Query("SELECT * FROM PharmaceuticalSpecialties")
    fun getAll(): List<PharmaceuticalSpecialtiesEntity>

    /**
     * Récupère une spécialité pharmaceutique spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant de la spécialité pharmaceutique à récupérer.
     * @return L'entité de la spécialité pharmaceutique correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM PharmaceuticalSpecialties WHERE id = :id")
    fun getOne(id: String): PharmaceuticalSpecialtiesEntity

    /**
     * Récupère une spécialité pharmaceutique spécifique de la base de données par le code cis du médicament associé.
     *
     * @param cisCode Le code cis du médicament associé à la spécialité pharmaceutique à récupérer.
     * @return Les entités de la spécialité pharmaceutique correspondant au code cis donné.
     */
    @Query("SELECT * FROM PharmaceuticalSpecialties WHERE cisCode = :cisCode")
    fun getByCisCode(cisCode: String): List<PharmaceuticalSpecialtiesEntity>

    /**
     * Ajoute une nouvelle spécialité pharmaceutique à la base de données.
     * @param t La spécialité pharmaceutique à ajouter.
     */
    @Insert
    fun add(t: PharmaceuticalSpecialtiesEntity)

    /**
     * Supprime une spécialité pharmaceutique spécifique de la base de données.
     * @param t La spécialité pharmaceutique à supprimer.
     */
    @Delete
    fun delete(t: PharmaceuticalSpecialtiesEntity)

    /**
     * Met à jour les informations d'une spécialité pharmaceutique spécifique dans la base de données.
     * @param t La spécialité pharmaceutique à mettre à jour.
     */
    @Update
    fun update(t: PharmaceuticalSpecialtiesEntity)

}