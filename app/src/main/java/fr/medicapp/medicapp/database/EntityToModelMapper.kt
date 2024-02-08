package fr.medicapp.medicapp.database

interface EntityToModelMapper<Model> {
    fun convert(): Model
}