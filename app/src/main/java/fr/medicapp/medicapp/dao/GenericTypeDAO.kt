package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.GenericType

/**
 * DAO Permettant l'accès à la table GenericType
 * */
@Dao
interface GenericTypeDAO {

    @Query("SELECT * FROM GenericType")
    fun getGenericTypeAll(): List<GenericType>

    @Insert
    fun addGenericType(genericType: GenericType)

    @Delete
    fun deleteGenericType(genericType: GenericType)

    @Update
    fun updateGenericType(genericType: GenericType)
}
