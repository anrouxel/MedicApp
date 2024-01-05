package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.MedicationPresentationDAO
import fr.medicapp.medicapp.entity.MedicationPresentation

class MedicationPresentationRepository(private val medicationPresentationDAO: MedicationPresentationDAO) {

    fun getMedicationPresentationAll():List<MedicationPresentation>{
        return try {
            medicationPresentationDAO.getMedicationPresentationAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getMedicationPresentationOne(cisCode:String):MedicationPresentation?{
        return try {
            medicationPresentationDAO.getMedicationPresentationOne(cisCode)
        }catch (e: Exception){
            null
        }
    }

    fun addMedicationPresentation(medicationPresentation: MedicationPresentation): Pair<Boolean,String>{
        return try {
            medicationPresentationDAO.addMedicationPresentation(medicationPresentation)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Medication presentation already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteMedicationPresentation(medicationPresentation: MedicationPresentation): Pair<Boolean,String>{
        return try {
            medicationPresentationDAO.deleteMedicationPresentation(medicationPresentation)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Medication presentation doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateMedicationPresentation(medicationPresentation: MedicationPresentation): Pair<Boolean,String>{
        return try {
            medicationPresentationDAO.updateMedicationPresentation(medicationPresentation)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Medication presentation doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}