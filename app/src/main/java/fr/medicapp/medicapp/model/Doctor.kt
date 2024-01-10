package fr.medicapp.medicapp.model

import com.maxkeppeler.sheets.option.models.Option

data class Doctor(
    val id: Int? = null,

    val lastName: String,

    var firstName: String,
) {
    fun getFullName(): String {
        return "$firstName $lastName"
    }

    override fun toString(): String {
        return getFullName()
    }

    fun isValide(): Boolean {
        return lastName.isNotEmpty() && firstName.isNotEmpty()
    }

    fun getOption(): Option {
        return Option(
            titleText = getFullName(),
        )
    }
}
