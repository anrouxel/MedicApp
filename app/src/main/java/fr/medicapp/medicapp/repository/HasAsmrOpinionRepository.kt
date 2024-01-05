package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.HasAsmrOpinionDAO
import fr.medicapp.medicapp.entity.HasAsmrOpinion

class HasAsmrOpinionRepository(private val hasAsmrOpinionDAO: HasAsmrOpinionDAO) {

    fun getHasAsmrOpinionAll():List<HasAsmrOpinion>{
        return try {
            hasAsmrOpinionDAO.getHasAsmrOpinionAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getHasAsmrOpinionOne(cisCode: String): HasAsmrOpinion?{
        return try {
            hasAsmrOpinionDAO.getHasAsmrOpinionOne(cisCode)
        }catch (e: Exception){
            null
        }
    }

    fun addHasAsmrOpinion(hasAsmrOpinion: HasAsmrOpinion): Pair<Boolean,String>{
        return try {
            hasAsmrOpinionDAO.addHasAsmrOpinion(hasAsmrOpinion)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Type already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteHasAsmrOpinion(hasAsmrOpinion: HasAsmrOpinion): Pair<Boolean,String>{
        return try {
            hasAsmrOpinionDAO.deleteHasAsmrOpinion(hasAsmrOpinion)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Type doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateHasAsmrOpinion(hasAsmrOpinion: HasAsmrOpinion): Pair<Boolean,String>{
        return try {
            hasAsmrOpinionDAO.updateHasAsmrOpinion(hasAsmrOpinion)
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