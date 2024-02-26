package fr.medicapp.medicapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import fr.medicapp.medicapp.model.User

@Dao
interface UserDAO {
    @Transaction
    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Insert
    fun insert(user: User): Long

    @Insert
    fun insert(user: List<User>): List<Long>

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}
