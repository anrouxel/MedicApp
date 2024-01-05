package fr.medicapp.medicapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.medicapp.medicapp.dao.DoctorDAO
import fr.medicapp.medicapp.dao.GenericGroupDAO
import fr.medicapp.medicapp.dao.GenericTypeDAO
import fr.medicapp.medicapp.dao.GenericTypeInfoDAO
import fr.medicapp.medicapp.dao.HasAsmrOpinionDAO
import fr.medicapp.medicapp.dao.HasSmrOpinionDAO
import fr.medicapp.medicapp.dao.ImportantInformationDAO
import fr.medicapp.medicapp.dao.MedicationCompositionDAO
import fr.medicapp.medicapp.dao.MedicationDAO
import fr.medicapp.medicapp.dao.MedicationPresentationDAO
import fr.medicapp.medicapp.dao.PrescriptionDispensingConditionsDAO
import fr.medicapp.medicapp.dao.TransparencyCommissionOpinionLinkDAO
import fr.medicapp.medicapp.dao.UserDAO
import fr.medicapp.medicapp.entity.Doctor
import fr.medicapp.medicapp.entity.GenericGroup
import fr.medicapp.medicapp.entity.GenericType
import fr.medicapp.medicapp.entity.GenericTypeInfo
import fr.medicapp.medicapp.entity.HasAsmrOpinion
import fr.medicapp.medicapp.entity.HasSmrOpinion
import fr.medicapp.medicapp.entity.ImportantInformation
import fr.medicapp.medicapp.entity.Medication
import fr.medicapp.medicapp.entity.MedicationComposition
import fr.medicapp.medicapp.entity.MedicationPresentation
import fr.medicapp.medicapp.entity.PrescriptionDispensingConditions
import fr.medicapp.medicapp.entity.TransparencyCommissionOpinionLinks
import fr.medicapp.medicapp.entity.User

/**
 * Classe configurant la base de données selon les entities et leurs DAO associés
 * */
@Database(
    entities = [
        Doctor::class,
        GenericGroup::class,
        GenericType::class,
        GenericTypeInfo::class,
        HasAsmrOpinion::class,
        HasSmrOpinion::class,
        ImportantInformation::class,
        Medication::class,
        MedicationComposition::class,
        MedicationPresentation::class,
        PrescriptionDispensingConditions::class,
        TransparencyCommissionOpinionLinks::class,
        User::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun doctorDAO(): DoctorDAO
    abstract fun genericGroupDAO(): GenericGroupDAO
    abstract fun genericTypeDAO(): GenericTypeDAO
    abstract fun genericTypeInfoDAO(): GenericTypeInfoDAO
    abstract fun hasAsmrOpinionDAO(): HasAsmrOpinionDAO
    abstract fun hasSmrOpinionDAO(): HasSmrOpinionDAO
    abstract fun importantInformationDAO(): ImportantInformationDAO
    abstract fun medicationCompositionDAO(): MedicationCompositionDAO
    abstract fun medicationDAO(): MedicationDAO
    abstract fun medicationPresentationDAO(): MedicationPresentationDAO
    abstract fun prescriptionDispensingConditionsDAO(): PrescriptionDispensingConditionsDAO
    abstract fun transparencyCommissionOpinionLinkDAO(): TransparencyCommissionOpinionLinkDAO
    abstract fun userDAO(): UserDAO
}
