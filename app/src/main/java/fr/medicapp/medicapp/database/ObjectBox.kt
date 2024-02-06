package fr.medicapp.medicapp.database

import android.content.Context
import fr.medicapp.medicapp.entity.MyObjectBox
import io.objectbox.BoxStore

object ObjectBox {
    private var INSTANCE: BoxStore? = null

    fun getInstance(context: Context): BoxStore {
        return INSTANCE ?: synchronized(this) {
            val instance = MyObjectBox.builder()
                .androidContext(context.applicationContext)
                .build()
            INSTANCE = instance
            instance
        }
    }
}