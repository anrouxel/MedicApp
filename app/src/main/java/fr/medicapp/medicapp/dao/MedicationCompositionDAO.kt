package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.MedicationComposition

@Dao
interface MedicationCompositionDAO {

    @Query("SELECT * FROM MedicationComposition")
    fun getMedicationCompositions(): List<MedicationComposition>

    @Query("SELECT * FROM MedicationComposition mc WHERE mc.cisCode = :cisCode")
    fun getMedicationComposition(cisCode:String):MedicationComposition

    @Insert
    fun addMedicationComposition(medicationComposition: MedicationComposition)

    @Delete
    fun deleteMedicationComposition(medicationComposition: MedicationComposition)

    @Update
    fun updateMedicationComposition(medicationComposition: MedicationComposition)
}