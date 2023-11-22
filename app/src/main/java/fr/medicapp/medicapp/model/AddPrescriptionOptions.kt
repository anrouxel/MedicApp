package fr.medicapp.medicapp.model

abstract class AddPrescriptionOptions {

    abstract val route: String

    abstract val title: String

    abstract val options: List<AddPrescriptionOption>

    private var selectedOption: AddPrescriptionOption? = null

    fun getTitle(): String {
        return title
    }

    fun selectOption(option: AddPrescriptionOption) {
        selectedOption = option
    }

    fun isOptionSelected(option: AddPrescriptionOption): Boolean {
        return selectedOption == option
    }
}
