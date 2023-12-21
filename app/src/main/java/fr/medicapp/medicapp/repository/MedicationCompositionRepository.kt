package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.MedicationCompositionDAO
import fr.medicapp.medicapp.entity.MedicationComposition

class MedicationCompositionRepository(private val medicationCompositionDAO: MedicationCompositionDAO) {

    fun getMedicationCompositionAll(): List<MedicationComposition>{
        return try {
            medicationCompositionDAO.getMedicationCompositionAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getMedicationCompositionOne(cisCode:String): MedicationComposition?{
        return try {
            medicationCompositionDAO.getMedicationCompositionOne(cisCode)
        }catch (e: Exception){
            null
        }
    }

    fun addMedicationComposition(medicationComposition: MedicationComposition): Pair<Boolean,String>{
        return try {
            medicationCompositionDAO.addMedicationComposition(medicationComposition)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Medication Composition already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteMedicationComposition(medicationComposition: MedicationComposition): Pair<Boolean,String>{
        return try {
            medicationCompositionDAO.deleteMedicationComposition(medicationComposition)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Medication Composition doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateMedicationComposition(medicationComposition: MedicationComposition): Pair<Boolean,String>{
        return try {
            medicationCompositionDAO.updateMedicationComposition(medicationComposition)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Medication Composition doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}