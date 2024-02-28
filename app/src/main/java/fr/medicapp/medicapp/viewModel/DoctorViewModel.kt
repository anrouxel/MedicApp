package fr.medicapp.medicapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.medicapp.medicapp.api.address.apiInteractions.DoctorsSearch
import fr.medicapp.medicapp.model.prescription.Doctor
import kotlinx.coroutines.launch

class DoctorViewModel : ViewModel() {
    private val _doctors = MutableLiveData<List<Doctor>>()
    val doctors: LiveData<List<Doctor>> get() = _doctors

    fun searchDoctor(query: String) {
        viewModelScope.launch {
            val searchResults = DoctorsSearch().searchDoctor(query) { doctors ->
                _doctors.postValue(doctors)
            }
        }
    }
}
