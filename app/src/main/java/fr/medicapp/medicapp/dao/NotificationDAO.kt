package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.NotificationEntity

/**
 * Interface DAO pour la gestion des données des notifications.
 *
 * Cette interface fournit des méthodes pour interagir avec la table NotificationEntity dans la base de données.
 */
@Dao
interface NotificationDAO {

    /**
     * Récupère toutes les notifications de la base de données.
     *
     * @return Une liste de toutes les entités de notifications.
     */
    @Query("SELECT * FROM NotificationEntity")
    fun getAll(): List<NotificationEntity>

    /**
     * Récupère une notification spécifique de la base de données par son identifiant.
     *
     * @param id L'identifiant de la notification à récupérer.
     * @return L'entité de la notification correspondant à l'identifiant donné.
     */
    @Query("SELECT * FROM NotificationEntity WHERE id = :id")
    fun getOne(id: String): NotificationEntity

    /**
     * Récupère toutes les notifications associées à un médicament spécifique.
     *
     * @param medicament Le nom du médicament pour lequel récupérer les notifications.
     * @return Une liste de toutes les entités de notifications associées au médicament donné.
     */
    @Query("SELECT * FROM NotificationEntity WHERE medicationName = :medicament")
    fun getByMedicament(medicament: String): List<NotificationEntity>

    /**
     * Ajoute une nouvelle notification à la base de données.
     *
     * @param t L'entité de la notification à ajouter.
     */
    @Insert
    fun add(t: NotificationEntity)

    /**
     * Supprime une notification spécifique de la base de données.
     *
     * @param t L'entité de la notification à supprimer.
     */
    @Delete
    fun delete(t: NotificationEntity)

    /**
     * Met à jour les informations d'une notification spécifique dans la base de données.
     *
     * @param t L'entité de la notification à mettre à jour.
     */
    @Update
    fun update(t: NotificationEntity)
}
