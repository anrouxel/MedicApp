package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.PrescriptionPattern

@Dao
interface PrescriptionPatternDAO {

    @Query("SELECT * FROM PrescriptionPattern")
    fun getPrescriptionPatternAll():List<PrescriptionPattern>

    @Insert
    fun addPrescriptionPattern(prescriptionPattern: PrescriptionPattern)

    @Delete
    fun deletePrescriptionPattern(prescriptionPattern: PrescriptionPattern)

    @Update
    fun updatePrescriptionPattern(prescriptionPattern: PrescriptionPattern)

}