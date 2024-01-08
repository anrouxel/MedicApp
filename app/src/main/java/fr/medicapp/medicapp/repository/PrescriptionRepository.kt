package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.PrescriptionDAO
import fr.medicapp.medicapp.entity.Prescription

class PrescriptionRepository(private val prescriptionDAO: PrescriptionDAO) {

    fun getPrescriptionAll(): List<Prescription>{
        return try {
            prescriptionDAO.getPrescriptionAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getPrescriptionOne(id: Int): Prescription?{
        return try {
            prescriptionDAO.getPrescriptionOne(id)
        }catch (e:Exception){
            null
        }
    }

    fun addPrescription(prescription: Prescription): Pair<Boolean,String>{
        return try {
            prescriptionDAO.addPrescription(prescription)
            Pair(true,"Success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Prescription already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deletePrescription(prescription: Prescription): Pair<Boolean,String>{
        return try {
            prescriptionDAO.deletePrescription(prescription)
            Pair(true, "Success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Prescription doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updatePrescription(prescription: Prescription): Pair<Boolean,String>{
        return try {
            prescriptionDAO.updatePrescription(prescription)
            Pair(true,"Success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Prescription doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}