package fr.medicapp.medicapp.repository

import fr.medicapp.medicapp.dao.MedicationDAO
import fr.medicapp.medicapp.dao.UserDAO
import fr.medicapp.medicapp.entity.MedicationEntity
import fr.medicapp.medicapp.entity.UserEntity

class MedicationRepository(
    private val medicationDAO: MedicationDAO
) {
    fun getAll(): List<MedicationEntity> {
        return medicationDAO.getAll()
    }

    fun getAllWithoutNotTreadings(): List<MedicationEntity> {
        return medicationDAO.getAllWithoutNotTreadings()
    }

    fun getOne(id: String): MedicationEntity {
        return medicationDAO.getOne(id)
    }

    fun add(t: MedicationEntity) {
        medicationDAO.add(t)
    }

    fun addAll(vararg t: MedicationEntity) {
        medicationDAO.addAll(*t)
    }

    fun delete(t: MedicationEntity) {
        medicationDAO.delete(t)
    }

    fun deleteAll(vararg t: MedicationEntity) {
        medicationDAO.deleteAll(*t)
    }

    fun update(t: MedicationEntity) {
        medicationDAO.update(t)
    }

    fun updateAll(vararg t: MedicationEntity) {
        medicationDAO.updateAll(*t)
    }
}