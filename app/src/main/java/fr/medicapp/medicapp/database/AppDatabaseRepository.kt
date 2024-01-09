package fr.medicapp.medicapp.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import fr.medicapp.medicapp.MainActivity
import fr.medicapp.medicapp.repository.DoctorRepository
import fr.medicapp.medicapp.repository.DurationRepository
import fr.medicapp.medicapp.repository.FrequencyRepository
import fr.medicapp.medicapp.repository.GenericGroupRepository
import fr.medicapp.medicapp.repository.GenericTypeInfoRepository
import fr.medicapp.medicapp.repository.GenericTypeRepository
import fr.medicapp.medicapp.repository.HasAsmrOpinionRepository
import fr.medicapp.medicapp.repository.HasSmrOpinionRepository
import fr.medicapp.medicapp.repository.ImportantInformationRepository
import fr.medicapp.medicapp.repository.MedicationCompositionRepository
import fr.medicapp.medicapp.repository.MedicationPresentationRepository
import fr.medicapp.medicapp.repository.MedicationRepository
import fr.medicapp.medicapp.repository.PrescriptionDispensingConditionsRepository
import fr.medicapp.medicapp.repository.PrescriptionRepository
import fr.medicapp.medicapp.repository.TransparencyCommissionOpinionLinkRepository
import fr.medicapp.medicapp.repository.TreatmentRepository
import fr.medicapp.medicapp.repository.UserRepository

@RequiresApi(Build.VERSION_CODES.O)
class AppDatabaseRepository()
{
    private val appDatabase : AppDatabase = Room.databaseBuilder(
            context = MainActivity(),
            AppDatabase::class.java, "database-name"
        ).build()

    fun doctorRepository(): DoctorRepository{
        return DoctorRepository(appDatabase.doctorDAO())
    }

    fun durationRepository(): DurationRepository{
        return DurationRepository(appDatabase.durationDAO())
    }

    fun frequencyRepository(): FrequencyRepository{
        return FrequencyRepository(appDatabase.frequencyDAO())
    }

    fun genericGroupRepository(): GenericGroupRepository{
        return GenericGroupRepository(appDatabase.genericGroupDAO())
    }

    fun genericTypeRepository(): GenericTypeRepository{
        return GenericTypeRepository(appDatabase.genericTypeDAO())
    }

    fun genericTypeInfoRepository(): GenericTypeInfoRepository{
        return GenericTypeInfoRepository(appDatabase.genericTypeInfoDAO())
    }

    fun hasAsmrOpinionRepository(): HasAsmrOpinionRepository{
        return HasAsmrOpinionRepository(appDatabase.hasAsmrOpinionDAO())
    }

    fun hasSmrOpinionRepository(): HasSmrOpinionRepository{
        return HasSmrOpinionRepository(appDatabase.hasSmrOpinionDAO())
    }

    fun importantInformationRepository(): ImportantInformationRepository{
        return ImportantInformationRepository(appDatabase.importantInformationDAO())
    }

    fun medicationCompositionRepository(): MedicationCompositionRepository{
        return MedicationCompositionRepository(appDatabase.medicationCompositionDAO())
    }

    fun medicationRepository(): MedicationRepository{
        return MedicationRepository(appDatabase.medicationDAO())
    }

    fun medicationPresentationRepository(): MedicationPresentationRepository{
        return MedicationPresentationRepository(appDatabase.medicationPresentationDAO())
    }

    fun prescriptionRepository(): PrescriptionRepository{
        return PrescriptionRepository(appDatabase.prescriptionDAO())
    }

    fun prescriptionDispensingConditionsRepository(): PrescriptionDispensingConditionsRepository{
        return PrescriptionDispensingConditionsRepository(appDatabase.prescriptionDispensingConditionsDAO())
    }

    fun transparencyCommissionOpinionLinkRepository(): TransparencyCommissionOpinionLinkRepository{
        return TransparencyCommissionOpinionLinkRepository(appDatabase.transparencyCommissionOpinionLinkDAO())
    }

    fun treatmentRepository(): TreatmentRepository{
        return TreatmentRepository(appDatabase.treatmentDAO())
    }

    fun userRepository(): UserRepository{
        return UserRepository(appDatabase.userDAO())
    }

}