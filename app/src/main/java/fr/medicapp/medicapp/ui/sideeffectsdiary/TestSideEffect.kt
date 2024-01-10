package fr.medicapp.medicapp.ui.sideeffectsdiary

data class TestSideEffect (
    var medicament : String,
    var date : String,
    var effetsConstates : MutableList<String>,
    var description : String
)