package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.LabelDAO
import fr.medicapp.medicapp.entity.Label

class LabelRepository(private val labelDAO: LabelDAO) {

    fun getLabelAll():List<Label>{
        return try {
            labelDAO.getLabelAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun addLabel(label: Label): Pair<Boolean,String>{
        return try {
            labelDAO.addLabel(label)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Label already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteLabel(label: Label): Pair<Boolean,String>{
        return try {
            labelDAO.deleteLabel(label)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Label doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}