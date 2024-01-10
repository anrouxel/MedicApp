package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.PrescriptionEntity

@Dao
interface PrescriptionDAO : IDatabase<PrescriptionEntity> {
    @Query("SELECT * FROM PrescriptionEntity")
    override fun getAll(): List<PrescriptionEntity>

    @Query("SELECT * FROM PrescriptionEntity WHERE id = :id")
    override fun <E> getOne(id: E): PrescriptionEntity

    @Insert
    override fun add(t: PrescriptionEntity)

    @Insert
    override fun addAll(vararg t: PrescriptionEntity)

    @Delete
    override fun delete(t: PrescriptionEntity)

    @Delete
    override fun deleteAll(vararg t: PrescriptionEntity)

    @Update
    override fun update(t: PrescriptionEntity)

    @Update
    override fun updateAll(vararg t: PrescriptionEntity)
}