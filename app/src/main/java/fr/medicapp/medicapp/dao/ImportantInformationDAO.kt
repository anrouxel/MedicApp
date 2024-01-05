package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.ImportantInformation

/**
 * DAO Permettant l'accès à la table ImportantInformation
 * */
@Dao
interface ImportantInformationDAO {

    @Query("SELECT * FROM ImportantInformation")
    fun getImportantInformationAll(): List<ImportantInformation>

    @Query("SELECT * FROM ImportantInformation WHERE cisCode = :cisCode")
    fun getImportantInformationOne(cisCode: String): ImportantInformation

    @Insert
    fun addImportantInformation(importantInformation: ImportantInformation)

    @Delete
    fun deleteImportantInformation(importantInformation: ImportantInformation)

    @Update
    fun updateImportantInformation(importantInformation: ImportantInformation)
}
