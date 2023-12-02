package fr.medicapp.medicapp.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.model.AddPrescriptionOption
import fr.medicapp.medicapp.model.AddPrescriptionOptionRadioButton
import fr.medicapp.medicapp.model.AddPrescriptionOptions
import fr.medicapp.medicapp.model.AddPrescriptionOptionsRadioButton
import fr.medicapp.medicapp.model.AddPrescriptionType

class AddPrescriptionViewModel : ViewModel() {
    private var screens: MutableState<List<AddPrescriptionOptions>> = mutableStateOf(listOf())

    private var selectedScreen: MutableState<AddPrescriptionOptions?> = mutableStateOf(null)

    fun addScreen(option: AddPrescriptionOptions) {
        screens.value = screens.value + option
    }

    fun setSelectedScreen(route: String) {
        selectedScreen.value = screens.value.find { it.getRoute() == route }
    }

    private fun getSelectedScreen(): AddPrescriptionOptions? {
        return selectedScreen.value
    }

    fun getSelectedScreenTitle(): String {
        return getSelectedScreen()?.getTitle() ?: ""
    }

    fun getSelectedScreenOptions(): List<AddPrescriptionOption> {
        return getSelectedScreen()?.getOptions() ?: listOf()
    }

    fun getSelectedScreenType(): AddPrescriptionType? {
        return getSelectedScreen()?.getType()
    }

    fun getSelectedScreenValidation(): Boolean {
        return getSelectedScreen()?.getValidation() ?: false
    }

    fun getSelectedScreenNextRoute(): String {
        return getSelectedScreen()?.getNextRoute() ?: ""
    }

    fun isSelectedScreenOptionRadioButton(option: AddPrescriptionOptionRadioButton): Boolean {
        if (getSelectedScreenType() == AddPrescriptionType.RadioButtonOption) {
            val screen = getSelectedScreen() as AddPrescriptionOptionsRadioButton
            return screen.isSelected(option)
        }
        return false
    }

    fun setSelectedScreenOptionRadioButton(option: AddPrescriptionOptionRadioButton) {
        if (!isSelectedScreenOptionRadioButton(option)) {
            val options = getSelectedScreenOptions().filter { it != option }
            val screen = getSelectedScreen() as AddPrescriptionOptionsRadioButton
            screen.setOptionSelected(option)
        }
    }
}
