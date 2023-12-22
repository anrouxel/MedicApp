package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.Label


/**
 * DAO Permettant l'accès à la table Label
 * */
@Dao
interface LabelDAO {

    @Query("SELECT * FROM Label")
    fun getLabelAll(): List<Label>

    @Insert
    fun addLabel(label: Label)

    @Delete
    fun deleteLabel(label: Label)
}