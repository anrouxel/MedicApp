package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.ImportantInformationsEntity

/**
 * Interface DAO pour la gestion des données des informations importantes.
 *
 * Cette interface fournit des méthodes pour interagir avec la table InportantInformationEntity dans la base de données.
 */
@Dao
interface ImportantInformationsDAO {
    /**
     * Récupère toutes les informations importantes de la base de données.
     *
     * @return Une liste de toutes les entités d'informations importantes.
     */
    @Query("SELECT * FROM ImportantInformations")
    fun getAll(): List<ImportantInformationsEntity>

    /**
     * Récupère une information importante spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant de l'information importante à récupérer.
     * @return L'entité de l'information importante correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM ImportantInformations WHERE id = :id")
    fun getOne(id: String): ImportantInformationsEntity

    /**
     * Récupère une information importante spécifique de la base de données par le code cis du médicament associé.
     *
     * @param cisCode Le code cis du médicament associé à l'information importante à récupérer.
     * @return Liste d'entité de l'information importante correspondant au code cis donné.
     */
    @Query("SELECT * FROM ImportantInformations WHERE cisCode = :cisCode")
    fun getByCisCode(cisCode: String): List<ImportantInformationsEntity>

    /**
     * Ajoute une nouvelle information importante à la base de données.
     * @param t L'information importante à ajouter.
     */
    @Insert
    fun add(t: ImportantInformationsEntity)

    /**
     * Supprime une information importante spécifique de la base de données.
     * @param t L'information importante à supprimer.
     */
    @Delete
    fun delete(t: ImportantInformationsEntity)

    /**
     * Met à jour les informations d'une information importante spécifique dans la base de données.
     * @param t L'information importante à mettre à jour.
     */
    @Update
    fun update(t: ImportantInformationsEntity)

}