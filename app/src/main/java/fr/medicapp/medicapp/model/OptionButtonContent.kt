package fr.medicapp.medicapp.model

class OptionButtonContent(
    private val _title: String,
    private val _description: String?,
    private val _nextRoute: String?,
) {
    fun getTitle(): String {
        return _title
    }

    fun getDescription(): String? {
        return _description
    }

    fun getNextRoute(): String? {
        return _nextRoute
    }
}