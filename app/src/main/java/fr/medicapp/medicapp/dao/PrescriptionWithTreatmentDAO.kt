package fr.medicapp.medicapp.dao

import androidx.room.Dao
import fr.medicapp.medicapp.database.IDatabase
import fr.medicapp.medicapp.entity.PrescriptionWithTreatmentEntity

@Dao
interface PrescriptionWithTreatmentDAO : IDatabase<PrescriptionWithTreatmentEntity> {
    override fun getAll(): List<PrescriptionWithTreatmentEntity>

    override fun <E> getOne(id: E): PrescriptionWithTreatmentEntity

    override fun add(t: PrescriptionWithTreatmentEntity)

    override fun addAll(vararg t: PrescriptionWithTreatmentEntity)

    override fun delete(t: PrescriptionWithTreatmentEntity)

    override fun deleteAll(vararg t: PrescriptionWithTreatmentEntity)

    override fun update(t: PrescriptionWithTreatmentEntity)

    override fun updateAll(vararg t: PrescriptionWithTreatmentEntity)
}