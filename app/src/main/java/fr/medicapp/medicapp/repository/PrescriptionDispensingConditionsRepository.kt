package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.PrescriptionDispensingConditionsDAO
import fr.medicapp.medicapp.entity.PrescriptionDispensingConditions

class PrescriptionDispensingConditionsRepository(private val prescriptionDispensingConditionsDAO: PrescriptionDispensingConditionsDAO) {

    fun getPrescriptionDispensingConditionsAll():List<PrescriptionDispensingConditions>{
        return try {
            prescriptionDispensingConditionsDAO.getPrescriptionDispensingConditionsAll()
        }catch (e:Exception){
            emptyList()
        }
    }

    fun getPrescriptionDispensingConditionsOne(cisCode:String):PrescriptionDispensingConditions?{
        return try {
            prescriptionDispensingConditionsDAO.getPrescriptionDispensingConditionsOne(cisCode)
        }catch (e:Exception){
            null
        }
    }

    fun addPrescriptionDispensingConditions(prescriptionDispensingConditions: PrescriptionDispensingConditions): Pair<Boolean,String>{
        return try {
            prescriptionDispensingConditionsDAO.addPrescriptionDispensingConditions(prescriptionDispensingConditions)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Prescription dispensing conditions already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deletePrescriptionDispensingConditions(prescriptionDispensingConditions: PrescriptionDispensingConditions): Pair<Boolean,String>{
        return try {
            prescriptionDispensingConditionsDAO.deletePrescriptionDispensingConditions(prescriptionDispensingConditions)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Prescription dispensing conditions doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updatePrescriptionDispensingConditions(prescriptionDispensingConditions: PrescriptionDispensingConditions): Pair<Boolean,String>{
        return try {
            prescriptionDispensingConditionsDAO.updatePrescriptionDispensingConditions(prescriptionDispensingConditions)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Prescription dispensing conditions doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}