package fr.medicapp.medicapp.ui.prescription

/**
 * Cette fonction détecte s'il y a au moins une erreur dans la liste.
 * @param consult Une consultation
 * @return Un booléen : true si la liste est OK, false sinon
 * @author Quentin
 */
fun errorList(consult : TestConsultation) : Boolean {
    for (i in consult.medicaments) {
        if (i.erreur.isNotEmpty()) {
            return false
        }
    }
    return true
}