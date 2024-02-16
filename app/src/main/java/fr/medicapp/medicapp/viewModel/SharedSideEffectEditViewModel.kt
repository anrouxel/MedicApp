package fr.medicapp.medicapp.viewModel

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.ObjectBox
import fr.medicapp.medicapp.database.entity.NotificationEntity
import fr.medicapp.medicapp.database.entity.PrescriptionEntity
import fr.medicapp.medicapp.database.entity.PrescriptionEntity_
import fr.medicapp.medicapp.database.entity.SideEffectEntity
import fr.medicapp.medicapp.database.entity.SideEffectEntity_
import fr.medicapp.medicapp.database.entity.medication.MedicationEntity
import fr.medicapp.medicapp.database.entity.medication.MedicationEntity_
import fr.medicapp.medicapp.model.Alarm
import fr.medicapp.medicapp.model.Duration
import fr.medicapp.medicapp.model.Notification
import fr.medicapp.medicapp.model.OptionDialog
import fr.medicapp.medicapp.model.Prescription
import fr.medicapp.medicapp.model.SideEffect
import fr.medicapp.medicapp.notification.NotificationPrescriptionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.DayOfWeek
import java.time.LocalDate

/**
 * ViewModel partagé pour gérer l'état de l'ajout d'une prescription.
 * Il contient un état partagé qui est un flux de prescriptions.
 */
class SharedSideEffectEditViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _sharedState: MutableStateFlow<SideEffect> = MutableStateFlow(SideEffect())
    val sharedState: StateFlow<SideEffect> = _sharedState

    fun updatePrescription(newPrescription: OptionDialog, context: Context) {
        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(PrescriptionEntity::class.java)
        val prescription =
            store.query().equal(PrescriptionEntity_.id, newPrescription.id).build().findFirst()?.convert()
        val updatedPrescription = _sharedState.value.copy(prescription = prescription)
        _sharedState.value = updatedPrescription
    }

    fun updateDate(date: LocalDate) {
        val updatedPrescription = _sharedState.value.copy(date = date)
        _sharedState.value = updatedPrescription
    }

    fun updateDescription(description: String) {
        val updatedPrescription = _sharedState.value.copy(description = description)
        _sharedState.value = updatedPrescription
    }

    fun save(context: Context) {
        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(SideEffectEntity::class.java)
        val sideEffect = _sharedState.value.convert(context)
        val id = store.put(sideEffect)
        _sharedState.value = store.query().equal(SideEffectEntity_.id, id).build().findFirst()
            ?.convert() ?: SideEffect()
    }

    fun getPrescriptionList(context: Context): List<OptionDialog> {
        val boxStore = ObjectBox.getInstance(context)
        val store = boxStore.boxFor(PrescriptionEntity::class.java)
        return store.all.map { it.convert().toOptionDialog() }
    }
}
