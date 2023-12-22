package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.GenericGroupDAO
import fr.medicapp.medicapp.entity.GenericGroup

class GenericGroupRepository(private val genericGroupDAO: GenericGroupDAO) {

    fun getGenericGroupAll():List<GenericGroup>{
        return try {
            genericGroupDAO.getGenericGroupAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getGenericGroupOne(cisCode: String): GenericGroup?{
        return try {
            genericGroupDAO.getGenericGroupOne(cisCode)
        }catch (e: Exception){
            null
        }
    }

    fun addGenericGroup(genericGroup: GenericGroup): Pair<Boolean,String>{
        return try {
            genericGroupDAO.addGenericGroup(genericGroup)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Group already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteGenericGroup(genericGroup: GenericGroup): Pair<Boolean,String>{
        return try {
            genericGroupDAO.deleteGenericGroup(genericGroup)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Group doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateGenericGroup(genericGroup: GenericGroup): Pair<Boolean,String>{
        return try {
            genericGroupDAO.updateGenericGroup(genericGroup)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Group doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}