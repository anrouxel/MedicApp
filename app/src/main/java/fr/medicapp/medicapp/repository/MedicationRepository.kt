package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.MedicationDAO
import fr.medicapp.medicapp.entity.Medication

class MedicationRepository(private val medicationDAO: MedicationDAO) {

    fun getMedicationAll():List<Medication>{
        return try {
            medicationDAO.getMedicationAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getMedicationOne(cisCode: String):Medication?{
        return try {
            medicationDAO.getMedicationOne(cisCode)
        }catch (e: Exception){
            null
        }
    }

    fun addMedication(medication: Medication): Pair<Boolean,String>{
        return try {
            medicationDAO.addMedication(medication)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Medication already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteMedication(medication: Medication): Pair<Boolean,String>{
        return try {
            medicationDAO.deleteMedication(medication)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Medication doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateMedication(medication: Medication): Pair<Boolean,String>{
        return try {
            medicationDAO.updateMedication(medication)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Medication doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}