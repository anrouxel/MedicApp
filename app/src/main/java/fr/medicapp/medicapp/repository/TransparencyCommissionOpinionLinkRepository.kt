package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.TransparencyCommissionOpinionLinkDAO
import fr.medicapp.medicapp.entity.TransparencyCommissionOpinionLinks

class TransparencyCommissionOpinionLinkRepository(private val transparencyCommissionOpinionLinkDAO: TransparencyCommissionOpinionLinkDAO) {

    fun getTransparencyCommissionOpinionLinkAll():List<TransparencyCommissionOpinionLinks>{
        return try {
            transparencyCommissionOpinionLinkDAO.getTransparencyCommissionOpinionLinkAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getTransparencyCommissionOpinionLinkOne(hasDossierCode:String):TransparencyCommissionOpinionLinks?{
        return try {
            transparencyCommissionOpinionLinkDAO.getTransparencyCommissionOpinionLinkOne(hasDossierCode)
        }catch (e: Exception){
            null
        }
    }

    fun addTransparencyCommissionOpinionLink(transparencyCommissionOpinionLinks: TransparencyCommissionOpinionLinks): Pair<Boolean,String>{
        return try {
            transparencyCommissionOpinionLinkDAO.addTransparencyCommissionOpinionLink(transparencyCommissionOpinionLinks)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Transparency commission opinion link already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteTransparencyCommissionOpinionLink(transparencyCommissionOpinionLinks: TransparencyCommissionOpinionLinks): Pair<Boolean, String>{
        return try {
            transparencyCommissionOpinionLinkDAO.deleteTransparencyCommissionOpinionLink(transparencyCommissionOpinionLinks)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Transparency commission opinion link doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateTransparencyCommissionOpinionLink(transparencyCommissionOpinionLinks: TransparencyCommissionOpinionLinks): Pair<Boolean,String>{
        return try {
            transparencyCommissionOpinionLinkDAO.updateTransparencyCommissionOpinionLink(transparencyCommissionOpinionLinks)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Transparency commission opinion link doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}