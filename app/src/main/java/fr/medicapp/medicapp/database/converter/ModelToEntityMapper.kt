package fr.medicapp.medicapp.database.converter

import android.content.Context

fun interface ModelToEntityMapper<Entity> {
    fun convert(context: Context): Entity
}
