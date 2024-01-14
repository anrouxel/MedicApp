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
 * Cette classe représente la base de données de l'application.
 */
@Database(
    entities = [
        DoctorEntity::class,
        TreatmentEntity::class,
        PrescriptionEntity::class,
        UserEntity::class,
        SideEffectEntity::class,
        MedicationEntity::class,
        NotificationEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Donne accès à la table des médecins.
     * @return un objet DoctorDAO.
     */
    abstract fun doctorDAO(): DoctorDAO

    /**
     * Donne accès à la table des prescriptions.
     * @return un objet PrescriptionDAO.
     */
    abstract fun prescriptionDAO(): PrescriptionDAO

    /**
     * Donne accès à la table des traitements.
     * @return un objet TreatmentDAO.
     */
    abstract fun treatmentDAO(): TreatmentDAO

    /**
     * Donne accès à la table des utilisateurs.
     * @return un objet UserDAO.
     */
    abstract fun userDAO(): UserDAO

    /**
     * Donne accès à la table des effets secondaires.
     * @return un objet SideEffectDAO.
     */
    abstract fun sideEffectDAO(): SideEffectDAO

    /**
     * Donne accès à la table des médicaments.
     * @return un objet MedicationDAO.
     */
    abstract fun medicationDAO(): MedicationDAO

    /**
     * Donne accès à la table des notifications.
     * @return un objet NotificationDAO.
     */
    abstract fun notificationDAO(): NotificationDAO

    companion object {

        /**
         * Le nom de la base de données.
         */
        private const val DATABASE_NAME = "dataLocal.db"

        /**
         * L'instance de la base de données.
         */
        private var INSTANCE: AppDatabase? = null

        /**
         * Récupère une instance de la base de données.
         * Si aucune instance n'existe, une nouvelle est créée.
         *
         * @param context Le contexte de l'application.
         * @return Une instance de AppDatabase.
         */
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
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
