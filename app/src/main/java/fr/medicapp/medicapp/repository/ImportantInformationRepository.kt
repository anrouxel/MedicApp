package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.ImportantInformationDAO
import fr.medicapp.medicapp.entity.ImportantInformation

class ImportantInformationRepository(private val importantInformationDAO: ImportantInformationDAO) {

    fun getImportantInformationAll():List<ImportantInformation>{
        return try {
            importantInformationDAO.getImportantInformationAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getImportantInformationOne(cisCode: String): ImportantInformation?{
        return try {
            importantInformationDAO.getImportantInformationOne(cisCode)
        }catch (e: Exception){
            null
        }
    }

    fun addImportantInformation(importantInformation: ImportantInformation): Pair<Boolean,String>{
        return try {
            importantInformationDAO.addImportantInformation(importantInformation)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Type already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteImportantInformation(importantInformation: ImportantInformation): Pair<Boolean,String>{
        return try {
            importantInformationDAO.deleteImportantInformation(importantInformation)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Generic Type doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateImportantInformation(importantInformation: ImportantInformation): Pair<Boolean,String>{
        return try {
            importantInformationDAO.updateImportantInformation(importantInformation)
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