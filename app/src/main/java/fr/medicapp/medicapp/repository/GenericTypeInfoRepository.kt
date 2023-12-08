package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.GenericTypeInfoDAO
import fr.medicapp.medicapp.entity.GenericTypeInfo

class GenericTypeInfoRepository(private val genericTypeInfoDAO: GenericTypeInfoDAO) {

    fun getGenericTypeAll():List<GenericTypeInfo>{
        return try {
            genericTypeInfoDAO.getGenericTypeInfoAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getGenericTypeInfoOne(genericTypeId: Int): GenericTypeInfo?{
        return try {
            genericTypeInfoDAO.getGenericTypeInfoOne(genericTypeId)
        }catch (e: Exception){
            null
        }
    }

    fun addGenericType(genericTypeInfo: GenericTypeInfo): Pair<Boolean,String>{
        return try {
            genericTypeInfoDAO.addGenericTypeInfo(genericTypeInfo)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Type already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteGenericType(genericTypeInfo: GenericTypeInfo): Pair<Boolean,String>{
        return try {
            genericTypeInfoDAO.deleteGenericTypeInfo(genericTypeInfo)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Type doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateGenericType(genericTypeInfo: GenericTypeInfo): Pair<Boolean,String>{
        return try {
            genericTypeInfoDAO.updateGenericTypeInfo(genericTypeInfo)
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