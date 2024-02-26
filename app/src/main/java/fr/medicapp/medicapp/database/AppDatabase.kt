package fr.medicapp.medicapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.medicapp.medicapp.database.converter.LocalDateConverter
import fr.medicapp.medicapp.database.converter.LocalTimeConverter
import fr.medicapp.medicapp.database.converter.MutableListDayOfWeekConverter
import fr.medicapp.medicapp.database.converter.MutableListFloatConverter
import fr.medicapp.medicapp.database.converter.MutableListStringConverter
import fr.medicapp.medicapp.database.dao.UserDAO
import fr.medicapp.medicapp.database.dao.medication.GenericGroupDAO
import fr.medicapp.medicapp.database.dao.medication.HasAsmrOpinionDAO
import fr.medicapp.medicapp.database.dao.medication.HasSmrOpinionDAO
import fr.medicapp.medicapp.database.dao.medication.ImportantInformationDAO
import fr.medicapp.medicapp.database.dao.medication.MedicationCompositionDAO
import fr.medicapp.medicapp.database.dao.medication.MedicationDAO
import fr.medicapp.medicapp.database.dao.medication.MedicationPresentationDAO
import fr.medicapp.medicapp.database.dao.medication.PharmaceuticalSpecialtyDAO
import fr.medicapp.medicapp.database.dao.medication.PrescriptionDispensingConditionsDAO
import fr.medicapp.medicapp.database.dao.medication.TransparencyCommissionOpinionLinksDAO
import fr.medicapp.medicapp.database.dao.medication.crossRef.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO
import fr.medicapp.medicapp.database.dao.medication.crossRef.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO
import fr.medicapp.medicapp.database.dao.prescription.AlarmDAO
import fr.medicapp.medicapp.database.dao.prescription.DoctorDAO
import fr.medicapp.medicapp.database.dao.prescription.DurationDAO
import fr.medicapp.medicapp.database.dao.prescription.NotificationDAO
import fr.medicapp.medicapp.database.dao.prescription.PrescriptionDAO
import fr.medicapp.medicapp.database.dao.prescription.SideEffectDAO
import fr.medicapp.medicapp.database.dao.prescription.crossRef.NotificationAlarmCrossRefDAO
import fr.medicapp.medicapp.model.User
import fr.medicapp.medicapp.model.medication.GenericGroup
import fr.medicapp.medicapp.model.medication.HasAsmrOpinionInformation
import fr.medicapp.medicapp.model.medication.HasSmrOpinionInformation
import fr.medicapp.medicapp.model.medication.ImportantInformation
import fr.medicapp.medicapp.model.medication.MedicationComposition
import fr.medicapp.medicapp.model.medication.MedicationInformation
import fr.medicapp.medicapp.model.medication.MedicationPresentation
import fr.medicapp.medicapp.model.medication.PharmaceuticalSpecialty
import fr.medicapp.medicapp.model.medication.PrescriptionDispensingConditions
import fr.medicapp.medicapp.model.medication.TransparencyCommissionOpinionLinks
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef
import fr.medicapp.medicapp.model.prescription.Alarm
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.Duration
import fr.medicapp.medicapp.model.prescription.NotificationInformation
import fr.medicapp.medicapp.model.prescription.PrescriptionInformation
import fr.medicapp.medicapp.model.prescription.SideEffectInformation
import fr.medicapp.medicapp.model.prescription.relationship.crossRef.NotificationAlarmCrossRef

/**
 * Cette classe représente la base de données de l'application.
 */
@Database(
    entities = [
        GenericGroup::class,
        HasAsmrOpinionInformation::class,
        HasSmrOpinionInformation::class,
        ImportantInformation::class,
        MedicationInformation::class,
        MedicationComposition::class,
        MedicationPresentation::class,
        PharmaceuticalSpecialty::class,
        PrescriptionDispensingConditions::class,
        TransparencyCommissionOpinionLinks::class,
        Alarm::class,
        Doctor::class,
        Duration::class,
        NotificationInformation::class,
        PrescriptionInformation::class,
        SideEffectInformation::class,
        HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef::class,
        HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef::class,
        NotificationAlarmCrossRef::class,
        User::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    LocalDateConverter::class,
    LocalTimeConverter::class,
    MutableListStringConverter::class,
    MutableListFloatConverter::class,
    MutableListDayOfWeekConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun prescriptionDAO(): PrescriptionDAO

    abstract fun doctorDAO(): DoctorDAO

    abstract fun durationDAO(): DurationDAO

    abstract fun medicationDAO(): MedicationDAO

    abstract fun sideEffectDAO(): SideEffectDAO

    abstract fun notificationDAO(): NotificationDAO

    abstract fun alarmDAO(): AlarmDAO

    abstract fun notificationAlarmCrossRefDAO(): NotificationAlarmCrossRefDAO

    abstract fun GenericGroupDAO(): GenericGroupDAO

    abstract fun HasAsmrOpinionDAO(): HasAsmrOpinionDAO

    abstract fun HasSmrOpinionDAO(): HasSmrOpinionDAO

    abstract fun ImportantInformationDAO(): ImportantInformationDAO

    abstract fun MedicationCompositionDAO(): MedicationCompositionDAO

    abstract fun MedicationPresentationDAO(): MedicationPresentationDAO

    abstract fun PharmaceuticalSpecialtyDAO(): PharmaceuticalSpecialtyDAO

    abstract fun PrescriptionDispensingConditionsDAO(): PrescriptionDispensingConditionsDAO

    abstract fun TransparencyCommissionOpinionLinksDAO(): TransparencyCommissionOpinionLinksDAO

    abstract fun HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO(): HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO

    abstract fun HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO(): HasSmrOpinionTransparencyCommissionOpinionLinksCrossRefDAO

    abstract fun userDAO(): UserDAO

    companion object {

        /**
         * Le nom de la base de données.
         */
        private const val DATABASE_NAME = "database.db"

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
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}