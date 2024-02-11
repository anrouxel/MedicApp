package fr.medicapp.medicapp.database.converter

interface ModelToEntityMapper<Entity> {
    fun convert(): Entity
}