package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.GenericTypeInfo

@Dao
interface GenericTypeInfoDAO {

    @Query("SELECT * FROM GenericTypeInfo")
    fun getGenericTypeInfos(): List<GenericTypeInfo>

    @Query("SELECT * FROM GenericTypeInfo WHERE genericTypeId = :genericTypeId")
    fun getGenericTypeInfo(genericTypeId: Int): GenericTypeInfo

    @Delete
    fun deleteGenericTypeInfo(genericTypeInfo: GenericTypeInfo)

    @Insert
    fun addGenericTypeInfo(genericTypeInfo: GenericTypeInfo)

    @Update
    fun updateGenericTypeInfo(genericTypeInfo: GenericTypeInfo)
}