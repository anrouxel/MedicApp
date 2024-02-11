package fr.medicapp.medicapp.database.converter

interface EntityToModelMapper<Model> {
    fun convert(): Model
}