package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.MedicationPresentation


/**
 * DAO Permettant l'accès à la table MedicationPresentation
 * */
@Dao
interface MedicationPresentationDAO {

    @Query("SELECT * FROM MedicationPresentation")
    fun getMedicationPresentationAll():List<MedicationPresentation>

    @Query("SELECT * FROM MedicationPresentation mp WHERE mp.cisCode = :cisCode")
    fun getMedicationPresentationOne(cisCode:String):MedicationPresentation

    @Insert
    fun addMedicationPresentation(medicationPresentation: MedicationPresentation)

    @Delete
    fun deleteMedicationPresentation(medicationPresentation: MedicationPresentation)

    @Update
    fun updateMedicationPresentation(medicationPresentation: MedicationPresentation)
}