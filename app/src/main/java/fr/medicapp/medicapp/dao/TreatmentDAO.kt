package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.TreatmentEntity

@Dao
interface TreatmentDAO {

    @Query("SELECT * FROM TreatmentEntity")
    fun getAll(): List<TreatmentEntity>

    @Query("SELECT * FROM TreatmentEntity WHERE id = :id")
    fun getOne(id: String): TreatmentEntity

    @Insert
    fun add(t: TreatmentEntity)

    @Insert
    fun addAll(vararg t: TreatmentEntity)

    @Delete
    fun delete(t: TreatmentEntity)

    @Delete
    fun deleteAll(vararg t: TreatmentEntity)

    @Update
    fun update(t: TreatmentEntity)

    @Update
    fun updateAll(vararg t: TreatmentEntity)
}