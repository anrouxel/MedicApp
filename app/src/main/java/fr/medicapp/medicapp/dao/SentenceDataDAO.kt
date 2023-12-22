package fr.medicapp.medicapp.dao

import android.provider.Telephony.Mms.Sent
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.SentenceData

/**
 * DAO Permettant l'accès à la table SentenceData
 * */
@Dao
interface SentenceDataDAO {

    @Query("SELECT * FROM SentenceData")
    fun getSentenceDataAll():List<SentenceData>

    @Query("SELECT * FROM SentenceData sd WHERE sd.sentence = :sentence")
    fun getSentenceDataOne(sentence:String):SentenceData

    @Insert
    fun addSentenceData(sentenceData: SentenceData)

    @Delete
    fun deleteSentenceData(sentenceData: SentenceData)

    @Update
    fun updateSentenceData(sentenceData: SentenceData)
}