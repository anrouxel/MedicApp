package fr.medicapp.medicapp.database

interface IDatabase<T> {
    fun getAll(): List<T>

    fun <E> getOne(id: E): T

    fun add(t: T)

    fun addAll(vararg t: T)

    fun delete(t: T)

    fun deleteAll(vararg t: T)

    fun update(t: T)

    fun updateAll(vararg t: T)
}
