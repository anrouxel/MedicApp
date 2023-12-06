package fr.medicapp.medicapp.model

class Doctor {
    private var _firstName: String = ""
    private var _lastName: String = ""

    fun getFirstName(): String {
        return _firstName
    }

    fun setFirstName(firstName: String) {
        _firstName = firstName
    }

    fun getLastName(): String {
        return _lastName
    }

    fun setLastName(lastName: String) {
        _lastName = lastName
    }
}
