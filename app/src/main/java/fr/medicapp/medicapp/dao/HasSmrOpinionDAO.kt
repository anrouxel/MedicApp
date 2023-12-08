package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.HasSmrOpinion

@Dao
interface HasSmrOpinionDAO {

    @Query("SELECT * FROM HasSmrOpinion")
    fun getHasSmrOpinionAll(): List<HasSmrOpinion>

    @Query("SELECT * FROM HasSmrOpinion WHERE cisCode = :cisCode")
    fun getHasSmrOpinionOne(cisCode: String): HasSmrOpinion

    @Insert
    fun addHasSmrOpinion(hasSmrOpinion: HasSmrOpinion)

    @Delete
    fun deleteHasSmrOpinion(hasSmrOpinion: HasSmrOpinion)

    @Update
    fun updateHasSmrOpinion(hasSmrOpinion: HasSmrOpinion)
}