package fr.medicapp.medicapp.database.dao.relations

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fr.medicapp.medicapp.model.relations.Interactions

@Dao
interface InteractionsDAO {

    @Transaction
    @Query("SELECT * FROM Interactions")
    fun getAll(): List<Interactions>

    @Insert
    fun insert(interactions: Interactions) : Long

    @Insert
    fun insert(interactions: List<Interactions>) : List<Long>

    @Delete
    fun delete(interactions: Interactions)

}