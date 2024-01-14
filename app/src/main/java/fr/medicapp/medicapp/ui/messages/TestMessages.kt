package fr.medicapp.medicapp.ui.messages

/**
 * Classe de données représentant un message de test dans l'application MedicApp.
 *
 * Chaque message de test est défini par :
 * - un destinataire
 * - un indicateur de lecture du message
 * - une liste de messages
 *
 * @property destinataire Le destinataire du message.
 * @property messageVu Indicateur si le message a été lu ou non.
 * @property messages La liste des messages.
 */
data class TestMessages(

    /**
     * Le destinataire des messages.
     */
    var destinataire: String,

    /**
     * Indicateur si le message a été lu ou non.
     */
    var messageVu: Boolean,

    /**
     * La liste des messages.
     */
    var messages: List<String>
)
