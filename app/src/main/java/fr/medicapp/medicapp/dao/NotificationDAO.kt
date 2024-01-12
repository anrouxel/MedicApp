package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.DoctorEntity
import fr.medicapp.medicapp.entity.NotificationEntity
import fr.medicapp.medicapp.entity.SideEffectEntity

@Dao
interface NotificationDAO {
    @Query("SELECT * FROM NotificationEntity")
    fun getAll(): List<NotificationEntity>

    @Query("SELECT * FROM NotificationEntity WHERE id = :id")
    fun getOne(id: String): NotificationEntity

    @Query("SELECT * FROM NotificationEntity WHERE medicationName = :medicament")
    fun getByMedicament(medicament: String): List<NotificationEntity>


    @Insert
    fun add(t: NotificationEntity)

    @Insert
    fun addAll(vararg t: NotificationEntity)

    @Delete
    fun delete(t: NotificationEntity)

    @Delete
    fun deleteAll(vararg t: NotificationEntity)

    @Update
    fun update(t: NotificationEntity)

    @Update
    fun updateAll(vararg t: NotificationEntity)
}
