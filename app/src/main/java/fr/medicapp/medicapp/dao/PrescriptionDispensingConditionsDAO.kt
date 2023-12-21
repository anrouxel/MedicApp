package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.PrescriptionDispensingConditions


/**
 * DAO Permettant l'accès à la table PrescriptionDispensingConditions
 * */
@Dao
interface PrescriptionDispensingConditionsDAO {

    @Query("SELECT * FROM PrescriptionDispensingConditions")
    fun getPrescriptionDispensingConditionsAll():List<PrescriptionDispensingConditions>

    @Query("SELECT * FROM PrescriptionDispensingConditions pdc WHERE pdc.cisCode = :cisCode")
    fun getPrescriptionDispensingConditionsOne(cisCode:String):PrescriptionDispensingConditions

    @Insert
    fun addPrescriptionDispensingConditions(prescriptionDispensingConditions: PrescriptionDispensingConditions)

    @Delete
    fun deletePrescriptionDispensingConditions(prescriptionDispensingConditions: PrescriptionDispensingConditions)

    @Update
    fun updatePrescriptionDispensingConditions(prescriptionDispensingConditions: PrescriptionDispensingConditions)
}