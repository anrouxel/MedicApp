package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.HasSmrOpinionDAO
import fr.medicapp.medicapp.entity.HasSmrOpinion

class HasSmrOpinionRepository(private val hasSmrOpinionDAO: HasSmrOpinionDAO) {
    fun getHasSmrOpinionAll():List<HasSmrOpinion>{
        return try {
            hasSmrOpinionDAO.getHasSmrOpinionAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getHasSmrOpinionOne(cisCode: String): HasSmrOpinion?{
        return try {
            hasSmrOpinionDAO.getHasSmrOpinionOne(cisCode)
        }catch (e: Exception){
            null
        }
    }

    fun addHasSmrOpinion(hasSmrOpinion: HasSmrOpinion): Pair<Boolean,String>{
        return try {
            hasSmrOpinionDAO.addHasSmrOpinion(hasSmrOpinion)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Type already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteHasSmrOpinion(hasSmrOpinion: HasSmrOpinion): Pair<Boolean,String>{
        return try {
            hasSmrOpinionDAO.deleteHasSmrOpinion(hasSmrOpinion)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Type doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateHasSmrOpinion(hasSmrOpinion: HasSmrOpinion): Pair<Boolean,String>{
        return try {
            hasSmrOpinionDAO.updateHasSmrOpinion(hasSmrOpinion)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Type doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}