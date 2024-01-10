package fr.medicapp.medicapp.dao

import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.PrescriptionAndDoctorEntity

interface PrescriptionAndDoctorDAO : IDatabase<PrescriptionAndDoctorEntity> {
    override fun getAll(): List<PrescriptionAndDoctorEntity>

    override fun <E> getOne(id: E): PrescriptionAndDoctorEntity

    override fun add(t: PrescriptionAndDoctorEntity)

    override fun addAll(vararg t: PrescriptionAndDoctorEntity)

    override fun delete(t: PrescriptionAndDoctorEntity)

    override fun deleteAll(vararg t: PrescriptionAndDoctorEntity)

    override fun update(t: PrescriptionAndDoctorEntity)

    override fun updateAll(vararg t: PrescriptionAndDoctorEntity)
}