package fr.medicapp.medicapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.medicapp.medicapp.dao.DoctorDAO
import fr.medicapp.medicapp.dao.MedicationDAO
import fr.medicapp.medicapp.dao.NotificationDAO
import fr.medicapp.medicapp.dao.PrescriptionDAO
import fr.medicapp.medicapp.dao.SideEffectDAO
import fr.medicapp.medicapp.dao.TreatmentDAO
import fr.medicapp.medicapp.dao.UserDAO
import fr.medicapp.medicapp.entity.DoctorEntity
import fr.medicapp.medicapp.entity.MedicationEntity
import fr.medicapp.medicapp.entity.NotificationEntity
import fr.medicapp.medicapp.entity.PrescriptionEntity
import fr.medicapp.medicapp.entity.SideEffectEntity
import fr.medicapp.medicapp.entity.TreatmentEntity
import fr.medicapp.medicapp.entity.UserEntity

/**
 * Classe configurant la base de données selon les entities et leurs DAO associés
 * */
@Database(
    entities = [
        DoctorEntity::class,
        TreatmentEntity::class,
        PrescriptionEntity::class,
        UserEntity::class,
        /*PrescriptionWithTreatmentEntity::class,
        PrescriptionAndDoctorEntity::class*/
        SideEffectEntity::class,
        MedicationEntity::class,
        NotificationEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun doctorDAO(): DoctorDAO
    abstract fun prescriptionDAO(): PrescriptionDAO
    abstract fun treatmentDAO(): TreatmentDAO
    abstract fun userDAO(): UserDAO
    /*abstract fun prescriptionWithTreatmentDAO(): PrescriptionWithTreatmentDAO
    abstract fun prescriptionAndDoctorDAO(): PrescriptionAndDoctorDAO*/
    abstract fun sideEffectDAO(): SideEffectDAO
    abstract fun medicationDAO(): MedicationDAO

    abstract fun notificationDAO(): NotificationDAO

    companion object {
        private const val DATABASE_NAME = "dataLocal.db"
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Créer la base de données si elle n'existe pas
                //Si on créé la base de données, alors on va la remplir avec les données de la base de données médicamenteuse
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).createFromAsset("dataLocal.db").build()
                INSTANCE = instance
                instance
            }
        }
    }
}
