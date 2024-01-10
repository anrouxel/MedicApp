package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.FrequencyDAO
import fr.medicapp.medicapp.entity.Frequency

class FrequencyRepository(private val frequencyDAO: FrequencyDAO) {

    fun getFrequencyAll(): List<Frequency>{
        return try {
            frequencyDAO.getFrequencyAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun addFrequency(frequency: Frequency): Pair<Boolean,String>{
        return try {
            frequencyDAO.addFrequency(frequency)
            Pair(true,"Add is a success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Frequency already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteFrequency(frequency: Frequency): Pair<Boolean,String>{
        return try {
            frequencyDAO.deleteFrequency(frequency)
            Pair(true,"Delete is a success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Frequency doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateFrequency(frequency: Frequency): Pair<Boolean,String>{
        return try {
            frequencyDAO.updateFrequency(frequency)
            Pair(true,"Update is a success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Frequency doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}