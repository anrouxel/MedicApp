package fr.medicapp.medicapp.database.dao.prescription

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.medicapp.medicapp.model.prescription.PrescriptionInformation
import fr.medicapp.medicapp.model.prescription.relationship.Prescription

@Dao
interface PrescriptionDAO {
    @Transaction
    @Query("SELECT * FROM PrescriptionInformation")
    fun getAll(): List<Prescription>

    @Transaction
    @Query("SELECT * FROM PrescriptionInformation WHERE prescription_id = :id")
    fun getById(id: Long): Prescription

    @Transaction
    @Query("SELECT p.* FROM PrescriptionInformation p INNER JOIN NotificationInformation n ON p.prescription_id = n.notification_id INNER JOIN notificationalarmcrossref na ON n.notification_id = na.notification_id INNER JOIN Alarm a ON na.alarm_id = a.alarm_id WHERE a.alarm_id = :alarmId")
    fun getByAlarmId(alarmId: Long): Prescription

    @Transaction
    @Query("SELECT p.* FROM PrescriptionInformation p INNER JOIN  MedicationInformation m ON p.medication_id = m.medication_id WHERE m.name LIKE :search || '%'")
    fun search(search: String): List<Prescription>

    @Insert
    fun insert(prescriptionInformation: PrescriptionInformation): Long

    @Update
    fun update(prescriptionInformation: PrescriptionInformation)

    @Delete
    fun delete(prescriptionInformation: PrescriptionInformation)
}
