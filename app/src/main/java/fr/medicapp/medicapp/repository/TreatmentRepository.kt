package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.TreatmentDAO
import fr.medicapp.medicapp.entity.Treatment

class TreatmentRepository(private val treatmentDAO: TreatmentDAO) {

    fun getTreatmentAll(): List<Treatment>{
        return try {
            treatmentDAO.getTreatmentAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getTreatmentOne(id: Int): Treatment?{
        return try {
            treatmentDAO.getTreatmentOne(id)
        }catch (e: Exception){
            null
        }
    }

    fun addTreatment(treatment: Treatment): Pair<Boolean,String>{
        return try {
            treatmentDAO.addTreatment(treatment)
            Pair(true, "Success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Treatment already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteTreatment(treatment: Treatment): Pair<Boolean,String>{
        return try {
            treatmentDAO.deleteTreatment(treatment)
            Pair(true,"Success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Treatment doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateTreatment(treatment: Treatment): Pair<Boolean,String>{
        return try {
            treatmentDAO.updateTreatment(treatment)
            Pair(true,"Success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Treatment doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}