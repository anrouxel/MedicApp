package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.SideEffectDAO
import fr.medicapp.medicapp.entity.SideEffectEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités SideEffect.
 *
 * @property sideEffectDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class SideEffectRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val sideEffectDao: SideEffectDAO
) {

    /**
     * Récupère tous les effets secondaires de la base de données.
     *
     * @return Une liste de toutes les entités SideEffect dans la base de données.
     */
    fun getAll(): List<SideEffectEntity> {
        return sideEffectDao.getAll()
    }

    /**
     * Récupère un effet secondaire spécifique de la base de données.
     *
     * @param id L'identifiant unique de l'effet secondaire à récupérer.
     * @return L'entité SideEffect correspondant à l'identifiant donné.
     */
    fun getOne(id: String): SideEffectEntity {
        return sideEffectDao.getOne(id)
    }

    /**
     * Récupère tous les effets secondaires associés à un médicament spécifique.
     *
     * @param medicament Le médicament pour lequel récupérer les effets secondaires.
     * @return Une liste de toutes les entités SideEffect associées au médicament donné.
     */
    fun getByMedicament(medicament: String): List<SideEffectEntity> {
        return sideEffectDao.getByMedicament(medicament)
    }

    /**
     * Ajoute une nouvelle entité SideEffect à la base de données.
     *
     * @param t L'entité SideEffect à ajouter à la base de données.
     */
    fun add(t: SideEffectEntity) {
        sideEffectDao.add(t)
    }

    /**
     * Supprime une entité SideEffect spécifique de la base de données.
     *
     * @param t L'entité SideEffect à supprimer de la base de données.
     */
    fun delete(t: SideEffectEntity) {
        sideEffectDao.delete(t)
    }

    /**
     * Met à jour une entité SideEffect spécifique dans la base de données.
     *
     * @param t L'entité SideEffect à mettre à jour dans la base de données.
     */
    fun update(t: SideEffectEntity) {
        sideEffectDao.update(t)
    }
}
