package fr.medicapp.medicapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

abstract class AddPrescriptionOptionsTextField : AddPrescriptionOptions() {
    override val _type: AddPrescriptionType = AddPrescriptionType.TextFieldOption

    protected abstract val _nextRoute: String

    override fun getValidation(): Boolean {
        val options = getOptions() as List<AddPrescriptionOptionTextField>
        return options.all { it.value.isNotEmpty() && it.value.isNotBlank() }
    }

    override fun getNextRoute(): String? {
        return _nextRoute
    }
}
