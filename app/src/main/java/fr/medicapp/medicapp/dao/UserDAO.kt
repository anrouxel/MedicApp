package fr.medicapp.medicapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import fr.medicapp.medicapp.entity.User

/**
 * DAO Permettant l'accès à la table User
 * */
@Dao
interface UserDAO {

    @Query("SELECT * FROM User")
    fun getUserAll(): List<User>

    @Query("SELECT * FROM User u WHERE u.lastName = :lastName AND u.firstName = :firstName")
    fun getUserWithName(lastName: String, firstName: String): User?

    @Insert
    fun addUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)
}