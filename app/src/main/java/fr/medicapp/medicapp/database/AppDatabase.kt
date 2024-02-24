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
import fr.medicapp.medicapp.database.dao.PrescriptionDAO
import fr.medicapp.medicapp.model.prescription.Alarm
import fr.medicapp.medicapp.model.prescription.Doctor
import fr.medicapp.medicapp.model.prescription.Duration
import fr.medicapp.medicapp.model.prescription.SideEffect
import fr.medicapp.medicapp.model.medication.GenericGroup
import fr.medicapp.medicapp.model.medication.HasAsmrOpinionInformation
import fr.medicapp.medicapp.model.medication.HasSmrOpinionInformation
import fr.medicapp.medicapp.model.medication.ImportantInformation
import fr.medicapp.medicapp.model.medication.MedicationInformation
import fr.medicapp.medicapp.model.medication.MedicationComposition
import fr.medicapp.medicapp.model.medication.MedicationPresentation
import fr.medicapp.medicapp.model.medication.PharmaceuticalSpecialty
import fr.medicapp.medicapp.model.medication.PrescriptionDispensingConditions
import fr.medicapp.medicapp.model.medication.TransparencyCommissionOpinionLinks
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationGenericGroupCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationImportantInformationCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationMedicationCompositionCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationMedicationPresentationCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationPharmaceuticalSpecialtyCrossRef
import fr.medicapp.medicapp.model.medication.relationship.crossRef.MedicationPrescriptionDispensingConditionsCrossRef
import fr.medicapp.medicapp.model.prescription.NotificationInformation
import fr.medicapp.medicapp.model.prescription.PrescriptionInformation
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
        SideEffect::class,
        HasAsmrOpinionTransparencyCommissionOpinionLinksCrossRef::class,
        HasSmrOpinionTransparencyCommissionOpinionLinksCrossRef::class,
        MedicationGenericGroupCrossRef::class,
        MedicationImportantInformationCrossRef::class,
        MedicationMedicationCompositionCrossRef::class,
        MedicationMedicationPresentationCrossRef::class,
        MedicationPharmaceuticalSpecialtyCrossRef::class,
        MedicationPrescriptionDispensingConditionsCrossRef::class,
        NotificationAlarmCrossRef::class,
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