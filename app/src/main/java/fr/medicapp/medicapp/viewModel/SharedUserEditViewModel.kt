package fr.medicapp.medicapp.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate

class SharedUserEditViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _sharedState: MutableStateFlow<User> = MutableStateFlow(User())
    val sharedState: MutableStateFlow<User> = _sharedState

    fun updateLastName(newLastName: String) {
        val updatedUser = _sharedState.value.copy(lastName = newLastName)
        _sharedState.value = updatedUser
    }

    fun updateFirstName(newFirstName: String) {
        val updatedUser = _sharedState.value.copy(firstName = newFirstName)
        _sharedState.value = updatedUser
    }

    fun updateBirthday(newBirthday: LocalDate) {
        val updatedUser = _sharedState.value.copy(birthday = newBirthday)
        _sharedState.value = updatedUser
    }

    fun updateGender(newGender: String) {
        val updatedUser = _sharedState.value
        _sharedState.value = updatedUser
    }

    fun updatePregnant(newPregnant: Boolean) {
        val updatedUser = _sharedState.value.copy(pregnant = newPregnant)
        _sharedState.value = updatedUser
    }

    fun updateAllergies(newAllergies: MutableList<String>) {
        val updatedUser = _sharedState.value.copy(allergies = newAllergies)
        _sharedState.value = updatedUser
    }

    fun addAllergy(newAllergy: String) {
        val updatedAllergies = _sharedState.value.allergies?.toMutableList()
        updatedAllergies?.add(newAllergy)
        val updatedUser = _sharedState.value.copy(allergies = updatedAllergies)
        _sharedState.value = updatedUser
    }
}