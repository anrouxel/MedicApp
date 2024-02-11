package fr.medicapp.medicapp.database.converter

fun interface ModelToEntityMapper<Entity> {
    fun convert(): Entity
}
