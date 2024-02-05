package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.HasSmrOpinionsDAO
import fr.medicapp.medicapp.entity.HasSmrOpinionsEntity

/**
 * Repository pour gérer les opérations de base de données pour les entités HasSmrOpinions.
 *
 * @property hasSmrOpinionsDao L'objet DAO pour accéder aux méthodes de base de données.

 */
class HasSmrOpinionsRepository(

    /**
     * L'objet DAO pour accéder aux méthodes de base de données.
     */
    private val hasSmrOpinionsDao: HasSmrOpinionsDAO
) {

            /**
            * Récupère toutes les entités HasSmrOpinions de la base de données.
            *
            * @return Une liste de toutes les entités HasSmrOpinions dans la base de données.
            */
            fun getAll(): List<HasSmrOpinionsEntity> {
                return hasSmrOpinionsDao.getAll()
            }

            /**
            * Récupère une entité HasSmrOpinions spécifique de la base de données.
            *
            * @param id L'identifiant unique de l'entité HasSmrOpinions à récupérer.
            * @return L'entité HasSmrOpinions correspondant à l'identifiant donné.
            */
            fun getOne(id: String): HasSmrOpinionsEntity {
                return hasSmrOpinionsDao.getOne(id)
            }

            /**
            * Ajoute une nouvelle entité HasSmrOpinions à la base de données.
            *
            * @param t L'entité HasSmrOpinions à ajouter à la base de données.
            */
            fun add(t: HasSmrOpinionsEntity) {
                hasSmrOpinionsDao.add(t)
            }

            /**
            * Supprime une entité HasSmrOpinions spécifique de la base de données.
            *
            * @param t L'entité HasSmrOpinions à supprimer de la base de données.
            */
            fun delete(t: HasSmrOpinionsEntity) {
                hasSmrOpinionsDao.delete(t)
            }

            /**
            * Met à jour les informations d'une entité HasSmrOpinions spécifique dans la base de données.
            *
            * @param t L'entité HasSmrOpinions à mettre à jour dans la base de données.
            */
            fun update(t: HasSmrOpinionsEntity) {
                hasSmrOpinionsDao.update(t)
            }
}