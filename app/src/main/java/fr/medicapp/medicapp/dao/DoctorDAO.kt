package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.DoctorEntity

/**
 * Interface DAO pour la gestion des données des médecins.
 *
 * Cette interface fournit des méthodes pour interagir avec la table DoctorEntity dans la base de données.
 */
@Dao
interface DoctorDAO {

    /**
     * Récupère tous les médecins de la base de données.
     *
     * @return Une liste de toutes les entités de médecins.
     */
    @Query("SELECT * FROM DoctorEntity")
    fun getAll(): List<DoctorEntity>

    /**
     * Récupère un médecin spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant du médecin à récupérer.
     * @return L'entité du médecin correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM DoctorEntity WHERE id = :id")
    fun getOne(id: String): DoctorEntity

    /**
     * Ajoute un nouveau médecin à la base de données.
     *
     * @param t L'entité du médecin à ajouter.
     */
    @Insert
    fun add(t: DoctorEntity)

    /**
     * Supprime un médecin spécifique de la base de données.
     *
     * @param t L'entité du médecin à supprimer.
     */
    @Delete
    fun delete(t: DoctorEntity)

    /**
     * Met à jour les informations d'un médecin spécifique dans la base de données.
     *
     * @param t L'entité du médecin à mettre à jour.
     */
    @Update
    fun update(t: DoctorEntity)
}
