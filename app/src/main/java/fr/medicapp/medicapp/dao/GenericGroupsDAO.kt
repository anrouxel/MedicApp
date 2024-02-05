package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.GenericGroupsEntity

/**
 * Interface DAO pour la gestion des données des groupes génériques.
 *
 * Cette interface fournit des méthodes pour interagir avec la table GenericGroupsEntity dans la base de données.
 */
@Dao
interface GenericGroupsDAO {
    /**
     * Récupère tous les groupes génériques de la base de données.
     *
     * @return Une liste de toutes les entités de groupes génériques.
     */
    @Query("SELECT * FROM GenericGroups")
    fun getAll(): List<GenericGroupsEntity>

    /**
     * Récupère un groupe générique spécifique de la base de données par son identifiant.
     *
     * @param genericGroupId L'identifiant du groupe générique à récupérer.
     * @return L'entité du groupe générique correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM GenericGroups WHERE genericGroupId = :id")
    fun getOne(id: String): GenericGroupsEntity

    /**
     * Récupère tous les groupes génériques associés à un cis code de médicament spécifique.
     * @param cisCode Le cis code du médicament pour lequel récupérer les groupes génériques.
     * @return Une liste de toutes les entités de groupes génériques associées au cis code donné.
     */
    @Query("SELECT * FROM GenericGroups WHERE cisCode = :cisCode")
    fun getByCisCode(cisCode: String): List<GenericGroupsEntity>


    /**
     * Ajoute un nouveau groupe générique à la base de données.
     * @param t L'entité du groupe générique à ajouter.
     */
    @Insert
    fun add(t: GenericGroupsEntity)

    /**
     * Supprime un groupe générique spécifique de la base de données.
     * @param t L'entité du groupe générique à supprimer.
     */
    @Delete
    fun delete(t: GenericGroupsEntity)

    /**
     * Met à jour les informations d'un groupe générique spécifique dans la base de données.
     * @param t L'entité du groupe générique à mettre à jour.
     */
    @Update
    fun update(t: GenericGroupsEntity)

}