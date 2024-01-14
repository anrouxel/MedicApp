package fr.medicapp.medicapp.security

import android.content.Context
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Classe pour gérer l'encryption des mots de passe.
 */
class EncryptionMotDePasse {

    /**
     * La valeur IV utilisée pour l'encryption et le décryptage.
     */
    private lateinit var ivValue: ByteArray

    /**
     * La clé utilisée pour l'encryption et le décryptage.
     */
    private val keyToEncrypt = ""

    /**
     * Méthode pour encrypter une chaîne de caractères.
     *
     * @param context Le contexte Android.
     * @param strToEncrypt La chaîne à encrypter.
     * @return Un tableau de bytes représentant la chaîne encryptée.
     */
    fun encrypt(context: Context, strToEncrypt: String): ByteArray {
        // Convertit la chaîne à encrypter en tableau de bytes.
        val plainText = strToEncrypt.toByteArray(Charsets.UTF_8)

        // Génère une clé à partir de la clé à encrypter.
        val key = generateKey(keyToEncrypt)

        // Crée une instance de Cipher pour l'encryption.
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")

        // Initialise le Cipher en mode ENCRYPT_MODE avec la clé générée.
        cipher.init(Cipher.ENCRYPT_MODE, key)

        // Encrypte le texte en clair et stocke le résultat dans cipherText.
        val cipherText = cipher.doFinal(plainText)

        // Stocke la valeur IV utilisée lors de l'encryption.
        ivValue = cipher.iv

        // Retourne le texte encrypté.
        return cipherText
    }

    /**
     * Méthode pour décrypter un tableau de bytes.
     *
     * @param context Le contexte Android.
     * @param dataToDecrypt Le tableau de bytes à décrypter.
     * @return Un tableau de bytes représentant la chaîne décryptée.
     */
    private fun decrypt(context: Context, dataToDecrypt: ByteArray): ByteArray {
        // Crée une instance de Cipher avec le schéma de chiffrement spécifié.
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")

        // Génère une clé à partir de la clé à chiffrer.
        val key = generateKey(keyToEncrypt)

        // Initialise le Cipher en mode DECRYPT_MODE avec la clé générée et un spécificateur IV.
        cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(ivValue))

        // Décrypte les données chiffrées et retourne le résultat.
        return cipher.doFinal(dataToDecrypt)
    }

    /**
     * Méthode pour générer une clé à partir d'un mot de passe.
     *
     * @param password Le mot de passe à utiliser pour générer la clé.
     * @return Une clé secrète spécifique.
     */
    private fun generateKey(password: String): SecretKeySpec {
        // Crée une instance de MessageDigest avec l'algorithme SHA-256.
        val digest: MessageDigest = MessageDigest.getInstance("SHA-256")

        // Convertit le mot de passe en tableau de bytes.
        val bytes = password.toByteArray()

        // Met à jour le digest avec le tableau de bytes du mot de passe.
        digest.update(bytes, 0, bytes.size)

        // Finalise le calcul du digest et retourne le résultat.
        val key = digest.digest()

        // Retourne une clé secrète spécifique à partir du digest.
        return SecretKeySpec(key, "AES")
    }
}
