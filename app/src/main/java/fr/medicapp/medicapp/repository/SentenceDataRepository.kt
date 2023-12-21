package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.SentenceDataDAO
import fr.medicapp.medicapp.entity.SentenceData

class SentenceDataRepository(private val sentenceDataDAO: SentenceDataDAO) {

    fun getSentenceDataAll():List<SentenceData>{
        return try {
            sentenceDataDAO.getSentenceDataAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getSentenceDataOne(sentence:String):SentenceData?{
        return try {
            sentenceDataDAO.getSentenceDataOne(sentence)
        }catch (e: Exception){
            null
        }
    }

    fun addSentenceData(sentenceData: SentenceData): Pair<Boolean,String>{
        return try {
            sentenceDataDAO.addSentenceData(sentenceData)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Sentence data already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteSentenceData(sentenceData: SentenceData): Pair<Boolean,String>{
        return try {
            sentenceDataDAO.deleteSentenceData(sentenceData)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Sentence data doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateSentenceData(sentenceData: SentenceData): Pair<Boolean,String>{
        return try {
            sentenceDataDAO.updateSentenceData(sentenceData)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"Sentence data doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}