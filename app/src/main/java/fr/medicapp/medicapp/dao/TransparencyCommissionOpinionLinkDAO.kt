package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.TransparencyCommissionOpinionLinks

/**
 * DAO Permettant l'accès à la table TransparencyCommissionOpinionLink
 * */
@Dao
interface TransparencyCommissionOpinionLinkDAO {

    @Query("SELECT * FROM TransparencyCommissionOpinionLinks")
    fun getTransparencyCommissionOpinionLinkAll(): List<TransparencyCommissionOpinionLinks>

    @Query("SELECT * FROM TransparencyCommissionOpinionLinks tcol WHERE tcol.hasDossierCode = :hasDossierCode")
    fun getTransparencyCommissionOpinionLinkOne(hasDossierCode: String): TransparencyCommissionOpinionLinks

    @Insert
    fun addTransparencyCommissionOpinionLink(transparencyCommissionOpinionLinks: TransparencyCommissionOpinionLinks)

    @Delete
    fun deleteTransparencyCommissionOpinionLink(transparencyCommissionOpinionLinks: TransparencyCommissionOpinionLinks)

    @Update
    fun updateTransparencyCommissionOpinionLink(transparencyCommissionOpinionLinks: TransparencyCommissionOpinionLinks)
}
