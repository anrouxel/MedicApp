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
    fun selectGenericGroups(): List<User>

    @Query("SELECT * FROM GenericGroup WHERE cisCode = :cisCode")
    fun selectGenericGroup(cisCode: String): User

    @Delete
    fun deleteGenericGroup(cisCode: String)

    @Insert
    fun addGenericGroup(genericGroup: GenericGroup)

    @Update
    fun updateGenericGroup(genericGroup: GenericGroup)
}