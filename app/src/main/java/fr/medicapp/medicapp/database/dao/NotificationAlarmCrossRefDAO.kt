package fr.medicapp.medicapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.relationship.crossRef.NotificationAlarmCrossRef

@Dao
interface NotificationAlarmCrossRefDAO {
    @Transaction
    @Query("SELECT * FROM NotificationAlarmCrossRef")
    fun getAll(): List<NotificationAlarmCrossRef>

    @Insert
    fun insert(notificationAlarmCrossRef: NotificationAlarmCrossRef): Long

    @Insert
    fun insert(notificationAlarmCrossRef: List<NotificationAlarmCrossRef>): List<Long>

    @Update
    fun update(notificationAlarmCrossRef: NotificationAlarmCrossRef)

    @Delete
    fun delete(notificationAlarmCrossRef: NotificationAlarmCrossRef)
}
