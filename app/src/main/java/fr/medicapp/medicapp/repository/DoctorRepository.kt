package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.DoctorDAO
import fr.medicapp.medicapp.entity.Doctor

class DoctorRepository(private val doctorDAO:DoctorDAO) {

    fun getDoctorAll(): List<Doctor> {
        return try {
            this.doctorDAO.getDoctorAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getDoctorOne(id: Int): Doctor?{
        return try{
            doctorDAO.getDoctorOne(id)
        }catch (e: Exception){
            null
        }
    }

    fun addDoctor(doctor: Doctor): Pair<Boolean,String>{
        return try {
            doctorDAO.addDoctor(doctor)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Doctor already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteDoctor(doctor: Doctor): Pair<Boolean,String>{
        return try {
            doctorDAO.deleteDoctor(doctor)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Doctor doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateDoctor(doctor: Doctor): Pair<Boolean,String>{
        return try {
            doctorDAO.updateDoctor(doctor)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Doctor doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}