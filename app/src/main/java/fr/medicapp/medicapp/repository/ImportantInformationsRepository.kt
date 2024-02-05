package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.ImportantInformationsDAO
import fr.medicapp.medicapp.entity.ImportantInformationsEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités ImportantInformations.
 *
 * @property importantInformationsDao L'objet DAO pour accéder aux méthodes de base de données.
 */
class ImportantInformationsRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val importantInformationsDao: ImportantInformationsDAO,
) {

    /**
     * Récupère toutes les entités ImportantInformations de la base de données.
     *
     * @return Une liste de toutes les entités ImportantInformations dans la base de données.
     */
    fun getAll(): List<ImportantInformationsEntity> {
        return importantInformationsDao.getAll()
    }

    /**
     * Récupère une entité ImportantInformations spécifique de la base de données.
     *
     * @param id L'identifiant unique de l'entité ImportantInformations à récupérer.
     * @return L'entité ImportantInformations correspondant à l'identifiant donné.
     */
    fun getOne(id: String): ImportantInformationsEntity {
        return importantInformationsDao.getOne(id)
    }

    /**
     * Récupère toutes les entités ImportantInformations associées au cis code d'un médicament spécifique.
     *
     * @param cisCodeMedicament Le Cis Code du médicament pour lequel récupérer les informations importantes.
     * @return Une entité ImportantInformations associées au médicament donné.
     */
    fun getByCisCode(cisCodeMedicament: String): ImportantInformationsEntity {
        return importantInformationsDao.getByCisCode(cisCodeMedicament)
    }

    /**
     * Ajoute une nouvelle entité ImportantInformations à la base de données.
     *
     * @param t L'entité ImportantInformations à ajouter à la base de données.
     */
    fun add(t: ImportantInformationsEntity) {
        importantInformationsDao.add(t)
    }

    /**
     * Supprime une entité ImportantInformations spécifique de la base de données.
     *
     * @param t L'entité ImportantInformations à supprimer de la base de données.
     */
    fun delete(t: ImportantInformationsEntity) {
        importantInformationsDao.delete(t)
    }

    /**
     * Met à jour les informations d'une entité ImportantInformations spécifique dans la base de données.
     *
     * @param t L'entité ImportantInformations à mettre à jour dans la base de données.
     */
    fun update(t: ImportantInformationsEntity) {
        importantInformationsDao.update(t)
    }


}