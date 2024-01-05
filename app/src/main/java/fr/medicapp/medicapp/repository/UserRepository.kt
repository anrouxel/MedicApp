package fr.medicapp.medicapp.repository

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import fr.medicapp.medicapp.dao.UserDAO
import fr.medicapp.medicapp.entity.User

class UserRepository(private val userDAO: UserDAO) {

    fun getUsers(): List<User>{
        return try {
            userDAO.getUserAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun getUserWithName(lastName: String, firstName: String): User?{
        return try {
            userDAO.getUserWithName(lastName,firstName)
        }catch (e: Exception){
            null
        }
    }

    fun addUser(user: User): Pair<Boolean,String>{
        return try {
            userDAO.addUser(user)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"User already exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun deleteUser(user: User): Pair<Boolean,String>{
        return try {
            userDAO.deleteUser(user)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"User doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }

    fun updateUser(user: User): Pair<Boolean,String>{
        return try {
            userDAO.updateUser(user)
            Pair(true,"Success")
        }catch (e: SQLiteConstraintException){
            Pair(false,"User doesn't exist !")
        }catch (e: SQLiteException){
            Pair(false,"Database error : ${e.message}")
        }catch(e: Exception){
            Pair(false,"Unknown error : ${e.message}")
        }
    }
}