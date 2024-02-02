package fr.medicapp.medicapp.model

import java.time.LocalDate

data class Medication(

    /**
     * L'identifiant unique (cisCode) du médicament.
     */
    val cisCode : String = "",

    /**
     * Le nom du médicament.
     */
    val name : String = "",

    /**
     * La forme pharmaceutique du médicament.
     */
    val pharmaceuticalForm : String = "",

    /**
     * Les voies d'administration du médicament.
     */
    val administrationRoutes : List<String> = emptyList(),

    /**
     * Le statut de l'autorisation de mise sur le marché du médicament.
     */
    val marketingAuthorizationStatus : String = "",

    /**
     * Le type de procédure d'autorisation de mise sur le marché du médicament.
     */
    val marketingAuthorizationProcedureType : String = "",

    /**
     * Le statut de commercialisation du médicament.
     */
    val commercializationStatus : String = "",

    /**
     * La date d'autorisation de mise sur le marché du médicament.
     */
    val marketingAuthorizationDate : LocalDate? = null,

    /**
     * Le statut BDM du médicament.
     */
    val bdmStatus : String = ""

    /**
     * Les détenteurs du mé
     */
)
