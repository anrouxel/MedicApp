package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.SideEffectEntity
import fr.medicapp.medicapp.entity.UserEntity

@Dao
interface SideEffectDAO {
    @Query("SELECT * FROM SideEffectEntity")
    fun getAll(): List<SideEffectEntity>

    @Query("SELECT * FROM SideEffectEntity WHERE id = :id")
    fun getOne(id: String): SideEffectEntity

    @Insert
    fun add(t: SideEffectEntity)

    @Insert
    fun addAll(vararg t: SideEffectEntity)

    @Delete
    fun delete(t: SideEffectEntity)

    @Delete
    fun deleteAll(vararg t: SideEffectEntity)

    @Update
    fun update(t: SideEffectEntity)

    @Update
    fun updateAll(vararg t: SideEffectEntity)
}