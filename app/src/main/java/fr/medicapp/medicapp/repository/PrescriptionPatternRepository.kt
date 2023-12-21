package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.PrescriptionPatternDAO
import fr.medicapp.medicapp.entity.PrescriptionPattern

class PrescriptionPatternRepository(private val prescriptionPatternDAO: PrescriptionPatternDAO) {

    fun getPrescriptionPatternAll():List<PrescriptionPattern>{
        return try {
            prescriptionPatternDAO.getPrescriptionPatternAll()
        }catch (e:Exception){
            emptyList()
        }
    }

    fun addPrescriptionPattern(prescriptionPattern: PrescriptionPattern): Pair<Boolean,String>{
        return try {
            prescriptionPatternDAO.addPrescriptionPattern(prescriptionPattern)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Prescription pattern already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deletePrescriptionPattern(prescriptionPattern: PrescriptionPattern): Pair<Boolean,String>{
        return try {
            prescriptionPatternDAO.deletePrescriptionPattern(prescriptionPattern)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Prescription pattern doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updatePrescriptionPattern(prescriptionPattern: PrescriptionPattern): Pair<Boolean,String>{
        return try {
            prescriptionPatternDAO.updatePrescriptionPattern(prescriptionPattern)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Prescription pattern doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}