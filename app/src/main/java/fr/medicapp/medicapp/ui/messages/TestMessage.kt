package fr.medicapp.medicapp.ui.messages

/**
 * Classe de données représentant un message de test dans l'application MedicApp.
 *
 * Chaque message de test est défini par :
 * - un contenu
 * - un émetteur
 *
 * @property contenu Le contenu du message.
 * @property emetteur L'émetteur du message.
 */
data class TestMessage(

    /**
     * Le contenu du message.
     */
    var contenu: String,

    /**
     * L'émetteur du message.
     */
    var emetteur: String
)
