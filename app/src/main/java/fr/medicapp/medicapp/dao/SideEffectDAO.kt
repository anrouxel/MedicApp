package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.SideEffectEntity

/**
 * Met à jour les informations d'une prescription spécifique dans la base de données.
 *
 * @param t L'entité de la prescription à mettre à jour.
 */
@Dao
interface SideEffectDAO {

    /**
     * Récupère tous les effets secondaires de la base de données.
     *
     * @return Une liste de toutes les entités d'effets secondaires.
     */
    @Query("SELECT * FROM SideEffectEntity")
    fun getAll(): List<SideEffectEntity>

    /**
     * Récupère tous les effets secondaires de la base de données.
     *
     * @return Une liste de toutes les entités d'effets secondaires.
     */
    @Query("SELECT * FROM SideEffectEntity WHERE id = :id")
    fun getOne(id: String): SideEffectEntity

    /**
     * Récupère tous les effets secondaires associés à un médicament spécifique.
     *
     * @param medicament Le nom du médicament pour lequel récupérer les effets secondaires.
     * @return Une liste de toutes les entités d'effets secondaires associées au médicament donné.
     */
    @Query("SELECT * FROM SideEffectEntity WHERE medicament = :medicament")
    fun getByMedicament(medicament: String): List<SideEffectEntity>

    /**
     * Ajoute un nouvel effet secondaire à la base de données.
     *
     * @param t L'entité de l'effet secondaire à ajouter.
     */
    @Insert
    fun add(t: SideEffectEntity)

    /**
     * Supprime un effet secondaire spécifique de la base de données.
     *
     * @param t L'entité de l'effet secondaire à supprimer.
     */
    @Delete
    fun delete(t: SideEffectEntity)

    /**
     * Met à jour les informations d'un effet secondaire spécifique dans la base de données.
     *
     * @param t L'entité de l'effet secondaire à mettre à jour.
     */
    @Update
    fun update(t: SideEffectEntity)
}