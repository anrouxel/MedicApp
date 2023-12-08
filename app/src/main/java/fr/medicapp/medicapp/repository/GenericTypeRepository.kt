package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.GenericTypeDAO
import fr.medicapp.medicapp.entity.GenericGroup
import fr.medicapp.medicapp.entity.GenericType

class GenericTypeRepository(private val genericTypeDAO: GenericTypeDAO) {

    fun getGenericTypeAll():List<GenericType>{
        return try {
            genericTypeDAO.getGenericTypeAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun addGenericType(genericType: GenericType): Pair<Boolean,String>{
        return try {
            genericTypeDAO.addGenericType(genericType)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Type already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteGenericType(genericType: GenericType): Pair<Boolean,String>{
        return try {
            genericTypeDAO.deleteGenericType(genericType)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Type doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateGenericType(genericType: GenericType): Pair<Boolean,String>{
        return try {
            genericTypeDAO.updateGenericType(genericType)
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