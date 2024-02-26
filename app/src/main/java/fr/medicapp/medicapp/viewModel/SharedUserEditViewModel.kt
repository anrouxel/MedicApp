package fr.medicapp.medicapp.viewModel

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.database.repositories.UserRepository
import fr.medicapp.medicapp.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
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
        val updatedUser = _sharedState.value.copy(gender = newGender)
        _sharedState.value = updatedUser
    }

    fun updatePregnant(newPregnant: Boolean) {
        val updatedUser = _sharedState.value.copy(pregnant = newPregnant)
        _sharedState.value = updatedUser
    }

    fun addAllergy() {
        val updatedAllergies = _sharedState.value.allergies.toMutableList()
        updatedAllergies.add("")
        val updatedUser = _sharedState.value.copy(allergies = updatedAllergies)
        _sharedState.value = updatedUser
    }

    fun removeAllergy(index: Int) {
        val updatedAllergies = _sharedState.value.allergies.toMutableList()
        updatedAllergies.removeAt(index)
        val updatedUser = _sharedState.value.copy(allergies = updatedAllergies)
        _sharedState.value = updatedUser
    }

    fun updateAllergy(it: String, index: Int) {
        val updatedAllergies = _sharedState.value.allergies.toMutableList()
        updatedAllergies[index] = it
        val updatedUser = _sharedState.value.copy(allergies = updatedAllergies)
        _sharedState.value = updatedUser
    }

    suspend fun save(context: Context) {
        withContext(Dispatchers.IO) {
            val user = _sharedState.value
            user.id = UserRepository(context).insert(user)
            context.getSharedPreferences("medicapp", Context.MODE_PRIVATE).edit().putBoolean("isUserCreated", true).apply()
            _sharedState.value = User()
        }
    }
}