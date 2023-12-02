package fr.medicapp.medicapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

abstract class AddPrescriptionOptions {

    abstract val _type: AddPrescriptionType

    protected abstract val _route: String

    protected abstract val _title: String

    protected abstract val _options: List<AddPrescriptionOption>

    fun getOptions(): List<AddPrescriptionOption> {
        return _options
    }

    fun getTitle(): String {
        return _title
    }

    fun getRoute(): String {
        return _route
    }

    fun getType(): AddPrescriptionType {
        return _type
    }

    abstract fun getValidation(): Boolean

    abstract fun getNextRoute(): String?
}
