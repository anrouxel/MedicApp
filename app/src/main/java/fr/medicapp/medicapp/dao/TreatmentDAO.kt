package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.Treatment

@Dao
interface TreatmentDAO {

    @Query("SELECT * FROM Treatment")
    fun getTreatmentAll():List<Treatment>

    @Query("SELECT * FROM Treatment t WHERE t.id = :id")
    fun getTreatmentOne(id:Int): Treatment?

    @Insert
    fun addTreatment(treatment: Treatment)

    @Delete
    fun deleteTreatment(treatment: Treatment)

    @Update
    fun updateTreatment(treatment: Treatment)
}