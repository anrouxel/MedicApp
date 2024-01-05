package fr.medicapp.medicapp.ui.prescription

data class TestMedicament(
    var nom : String,
    var posologie : String,
    var aRenouveler : Int,
    var quantiteSuffisantePour : String,
    var remboursable : Boolean,
    var notificationActive : Boolean,
    var erreur : String
)