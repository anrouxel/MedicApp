package fr.medicapp.medicapp.database.dao.prescription

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.medicapp.medicapp.model.prescription.NotificationInformation
import fr.medicapp.medicapp.model.prescription.relationship.Notification

@Dao
interface NotificationDAO {
    @Transaction
    @Query("SELECT * FROM NotificationInformation")
    fun getAll(): List<Notification>

    @Insert
    fun insert(notificationInformation: NotificationInformation): Long

    @Insert
    fun insert(notificationInformation: List<NotificationInformation>): List<Long>

    @Update
    fun update(notificationInformation: NotificationInformation)

    @Delete
    fun delete(notificationInformation: NotificationInformation)
}
