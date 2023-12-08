package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.GenericGroup
import fr.medicapp.medicapp.entity.User

@Dao
interface GenericGroupDAO {

    @Query("SELECT * FROM GenericGroup")
    fun getGenericGroupAll(): List<GenericGroup>

    @Query("SELECT * FROM GenericGroup WHERE cisCode = :cisCode")
    fun getGenericGroupOne(cisCode: String): GenericGroup

    @Delete
    fun deleteGenericGroup(genericGroup: GenericGroup)

    @Insert
    fun addGenericGroup(genericGroup: GenericGroup)

    @Update
    fun updateGenericGroup(genericGroup: GenericGroup)
}