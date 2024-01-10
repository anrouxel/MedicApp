package fr.medicapp.medicapp.ui.notifications

data class TestNotification (
    var nomMedicament : String,
    var frequence : String,
    var heures : MutableList<String>,
)