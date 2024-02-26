package fr.medicapp.medicapp.database.repositories

import android.content.Context
import fr.medicapp.medicapp.database.repositories.Repository
import fr.medicapp.medicapp.model.User
import fr.medicapp.medicapp.model.prescription.Doctor

class UserRepository(context: Context) : Repository(context = context) {
    fun getAll(): List<User> {
        return db.userDAO().getAll()
    }

    fun insert(user: User): Long {
        return db.userDAO().insert(user)
    }

    fun insert(users: List<User>): List<Long> {
        return db.userDAO().insert(users)
    }
}
