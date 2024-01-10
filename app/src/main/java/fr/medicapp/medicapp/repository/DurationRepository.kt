package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.DurationDAO
import fr.medicapp.medicapp.entity.Duration

class DurationRepository(private val durationDAO: DurationDAO) {

    fun getDurationAll(): List<Duration>{
        return try {
            durationDAO.getDurationAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun addDuration(duration: Duration): Pair<Boolean,String>{
        return try {
            durationDAO.addDuration(duration)
            Pair(true,"Add is a success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Duration already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteDuration(duration: Duration): Pair<Boolean,String>{
        return try {
            durationDAO.deleteDuration(duration)
            Pair(true,"Delete is a success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Duration doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateDuration(duration: Duration): Pair<Boolean,String>{
        return try {
            durationDAO.updateDuration(duration)
            Pair(true,"Update is a success !")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Duration doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}