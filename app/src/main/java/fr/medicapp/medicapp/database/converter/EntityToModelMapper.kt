package fr.medicapp.medicapp.database.converter

fun interface EntityToModelMapper<Model> {
    fun convert(): Model
}
