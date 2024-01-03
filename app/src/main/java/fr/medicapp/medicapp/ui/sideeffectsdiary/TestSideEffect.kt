package fr.medicapp.medicapp.ui.sideeffectsdiary

import java.time.LocalDate

data class TestSideEffect (
    var medicament : String,
    var date : String,
    var effetsConstates : List<String>,
    var description : String
)