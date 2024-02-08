package fr.medicapp.medicapp.database

interface ModelToEntityMapper<Model, Entity> {
    fun convert(model: Model): Entity
}