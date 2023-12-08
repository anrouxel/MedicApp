package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.HasAsmrOpinion

@Dao
interface HasAsmrOpinionDAO {

    @Query("SELECT * FROM HasAsmrOpinion")
    fun getHasAsmrOpinionAll(): List<HasAsmrOpinion>

    @Query("SELECT * FROM HasAsmrOpinion WHERE cisCode = :cisCode")
    fun getHasAsmrOpinionOne(cisCode: String): HasAsmrOpinion

    @Insert
    fun addHasAsmrOpinion(hasAsmrOpinion: HasAsmrOpinion)

    @Delete
    fun deleteHasAsmrOpinion(hasAsmrOpinion: HasAsmrOpinion)

    @Update
    fun updateHasAsmrOpinion(hasAsmrOpinion: HasAsmrOpinion)
}