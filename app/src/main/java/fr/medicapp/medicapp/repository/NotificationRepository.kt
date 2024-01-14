package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.NotificationDAO
import fr.medicapp.medicapp.entity.NotificationEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités Notification.
 *
 * @property notificationDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class NotificationRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val notificationDao: NotificationDAO
) {

    /**
     * Récupère toutes les notifications de la base de données.
     *
     * @return Une liste de toutes les entités Notification dans la base de données.
     */
    fun getAll(): List<NotificationEntity> {
        return notificationDao.getAll()
    }

    /**
     * Récupère une notification spécifique de la base de données.
     *
     * @param id L'identifiant unique de la notification à récupérer.
     * @return L'entité Notification correspondant à l'identifiant donné.
     */
    fun getOne(id: String): NotificationEntity {
        return notificationDao.getOne(id)
    }

    /**
     * Récupère toutes les notifications associées à un médicament spécifique.
     *
     * @param medicament Le médicament pour lequel récupérer les notifications.
     * @return Une liste de toutes les entités Notification associées au médicament donné.
     */
    fun getByMedicament(medicament: String): List<NotificationEntity> {
        return notificationDao.getByMedicament(medicament)
    }

    /**
     * Ajoute une nouvelle entité Notification à la base de données.
     *
     * @param t L'entité Notification à ajouter à la base de données.
     */
    fun add(t: NotificationEntity) {
        notificationDao.add(t)
    }

    /**
     * Supprime une entité Notification spécifique de la base de données.
     *
     * @param t L'entité Notification à supprimer de la base de données.
     */
    fun delete(t: NotificationEntity) {
        notificationDao.delete(t)
    }

    /**
     * Met à jour une entité Notification spécifique dans la base de données.
     *
     * @param t L'entité Notification à mettre à jour dans la base de données.
     */
    fun update(t: NotificationEntity) {
        notificationDao.update(t)
    }
}
