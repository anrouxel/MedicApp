package fr.medicapp.medicapp.database.dao.medication.crossRef

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef

@Dao
interface HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO {
    @Transaction
    @Query("SELECT * FROM HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef")
    fun getAll(): List<HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef>

    @Insert
    fun insert(hasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef: HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef): Long

    @Insert
    fun insert(hasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef: List<HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef>): List<Long>

    @Delete
    fun delete(hasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef: HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef)
}