package fr.medicapp.medicapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class AddPrescriptionOptionsRadioButton : AddPrescriptionOptions() {
    override val _type: AddPrescriptionType = AddPrescriptionType.RadioButtonOption

    private var _optionSelected by mutableStateOf<AddPrescriptionOptionRadioButton?>(null)

    override fun getValidation(): Boolean {
        return _optionSelected != null &&
            _optionSelected?.route != null &&
            _optionSelected?.route?.isNotEmpty() == true &&
            _optionSelected?.route?.isNotBlank() == true
    }

    override fun getNextRoute(): String? {
        return _optionSelected?.route
    }

    fun getOptionSelected(): AddPrescriptionOptionRadioButton? {
        return _optionSelected
    }

    fun setOptionSelected(option: AddPrescriptionOptionRadioButton) {
        _optionSelected = option
    }

    fun isSelected(option: AddPrescriptionOptionRadioButton): Boolean {
        return _optionSelected == option
    }
}
