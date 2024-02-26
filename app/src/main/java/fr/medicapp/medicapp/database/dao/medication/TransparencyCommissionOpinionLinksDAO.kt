package fr.medicapp.medicapp.database.dao.medication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.TransparencyCommissionOpinionLinks

@Dao
interface TransparencyCommissionOpinionLinksDAO {
    @Transaction
    @Query("SELECT * FROM TransparencyCommissionOpinionLinks")
    fun getAll(): List<TransparencyCommissionOpinionLinks>

    @Insert
    fun insert(transparencyCommissionOpinionLinks: TransparencyCommissionOpinionLinks): Long

    @Insert
    fun insert(transparencyCommissionOpinionLinks: List<TransparencyCommissionOpinionLinks>): List<Long>

    @Delete
    fun delete(transparencyCommissionOpinionLinks: TransparencyCommissionOpinionLinks)
}
