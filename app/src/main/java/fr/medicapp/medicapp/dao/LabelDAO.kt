package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.Label

@Dao
interface LabelDAO {

    @Query("SELECT * FROM Label")
    fun getLabel(): List<Label>

    @Insert
    fun addLabel(label: Label)

    @Delete
    fun deleteLabel(label: Label)
}