package fr.medicapp.medicapp.database.dao.prescription

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.medicapp.medicapp.model.prescription.SideEffectInformation
import fr.medicapp.medicapp.model.prescription.relationship.SideEffect

@Dao
interface SideEffectDAO {
    @Transaction
    @Query("SELECT * FROM SideEffectInformation")
    fun getAll(): List<SideEffect>

    @Transaction
    @Query("SELECT * FROM SideEffectInformation WHERE side_effect_id = :id")
    fun getById(id: Long): SideEffect

    @Insert
    fun insert(sideEffectInformation: SideEffectInformation): Long

    @Insert
    fun insert(sideEffectInformation: List<SideEffectInformation>): List<Long>

    @Update
    fun update(sideEffectInformation: SideEffectInformation)

    @Delete
    fun delete(sideEffectInformation: SideEffectInformation)
}
