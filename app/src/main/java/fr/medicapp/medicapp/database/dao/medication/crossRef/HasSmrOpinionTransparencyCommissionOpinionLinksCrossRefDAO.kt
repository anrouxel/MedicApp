package fr.medicapp.medicapp.database.dao.medication.crossRef

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef

@Dao
interface HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO {
    @Transaction
    @Query("SELECT * FROM HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef")
    fun getAll(): List<HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef>

    @Insert
    fun insert(hasSmrOpinionTransparencyCommissionOpinionLinksCrossRef: HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef): Long

    @Insert
    fun insert(hasSmrOpinionTransparencyCommissionOpinionLinksCrossRef: List<HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef>): List<Long>

    @Delete
    fun delete(hasSmrOpinionTransparencyCommissionOpinionLinksCrossRef: HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef)
}