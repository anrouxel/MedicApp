package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.TreatmentEntity

@Dao
interface TreatmentDAO : IDatabase<TreatmentEntity> {

    @Query("SELECT * FROM TreatmentEntity")
    override fun getAll(): List<TreatmentEntity>

    @Query("SELECT * FROM TreatmentEntity WHERE id = :id")
    override fun <E> getOne(id: E): TreatmentEntity

    @Insert
    override fun add(t: TreatmentEntity)

    @Insert
    override fun addAll(vararg t: TreatmentEntity)

    @Delete
    override fun delete(t: TreatmentEntity)

    @Delete
    override fun deleteAll(vararg t: TreatmentEntity)

    @Update
    override fun update(t: TreatmentEntity)

    @Update
    override fun updateAll(vararg t: TreatmentEntity)
}