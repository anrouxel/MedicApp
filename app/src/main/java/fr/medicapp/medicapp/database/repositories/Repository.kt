package fr.medicapp.medicapp.database.repositories

import android.content.Context
import fr.medicapp.medicapp.database.AppDatabase

abstract class Repository(
    protected val context: Context
) {
    protected val db = AppDatabase.getInstance(context)
}