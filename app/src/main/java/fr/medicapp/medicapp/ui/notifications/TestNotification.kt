package fr.medicapp.medicapp.ui.notifications

data class TestNotification (
    var medicationName : String,
    var frequency : String,
    var hours : MutableList<Int>,
    var minutes : MutableList<Int>
)