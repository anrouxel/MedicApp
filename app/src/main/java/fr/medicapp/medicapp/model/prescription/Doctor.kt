package fr.medicapp.medicapp.model.prescription

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Une classe modèle pour les médecins
 * @property id l'identifiant en base de données
 * @property nationalId l'identifiant national du médecin
 * @property idTypePP l'identifiant du type de PP
 * @property idPP l'identifiant du PP
 * @property civilCodeEx le code civil de la profession du médecin
 * @property civilLabelEx le libellé civil de la profession du médecin
 * @property civilCode le code civilité du médecin
 * @property civilLabel le libellé civil du médecin
 * @property lastName le nom du médecin
 * @property firstName le prénom du médecin
 * @property professionCode le code de la profession du médecin
 * @property professionLabel le libellé de la profession du médecin
 * @property professionalCategoryCode le code de la catégorie professionnelle du médecin
 * @property professionalCategoryLabel le libellé de la catégorie professionnelle du médecin
 * @property skillTypeCode le code du type de savoir-faire du médecin
 * @property skillTypeLabel le libellé du type de savoir-faire du médecin
 * @property skillCode le code du savoir-faire du médecin
 * @property skillLabel le libellé du savoir-faire du médecin
 * @property practiceModeCode le code du mode d'exercice du médecin
 * @property practiceModeLabel le libellé du mode d'exercice du médecin
 * @property siteSiretNumber le numéro de SIRET du site du médecin
 * @property siteSirenNumber le numéro de SIREN du site du médecin
 * @property siteFinessNumber le numéro de FINESS du site du médecin
 * @property legalEstablishmentFinessNumber le numéro de FINESS de l'établissement juridique du médecin
 * @property techStructureId l'identifiant technique de la structure du médecin
 * @property siteCompanyName le nom de la société du site du médecin
 * @property siteCommercialSign le signe commercial du site du médecin
 * @property structureRecipientComplement le complément destinataire de la structure du médecin
 * @property structureGeographicPointComplement le complément du point géographique de la structure du médecin
 * @property structureStreetNumber le numéro de voie de la structure du médecin
 * @property structureStreetRepeatIndex l'indice de répétition de la voie de la structure du médecin
 * @property structureStreetTypeCode le code du type de voie de la structure du médecin
 * @property structureStreetTypeLabel le libellé du type de voie de la structure du médecin
 * @property structureStreetLabel le libellé de la voie de la structure du médecin
 * @property structureDistributionMention la mention de distribution de la structure du médecin
 * @property structureCedexOffice le bureau Cedex de la structure du médecin
 * @property structurePostalCode le code postal de la structure du médecin
 * @property structureCommuneCode le code de la commune de la structure du médecin
 * @property structureCommuneLabel le libellé de la commune de la structure du médecin
 * @property structureCountryCode le code du pays de la structure du médecin
 * @property structureCountryLabel le libellé du pays de la structure du médecin
 * @property structurePhoneNumber le numéro de téléphone de la structure du médecin
 * @property structurePhoneNumber2 le deuxième numéro de téléphone de la structure du médecin
 * @property structureFaxNumber le numéro de fax de la structure du médecin
 * @property structureEmailAddress l'adresse e-mail de la structure du médecin
 * @property structureDepartmentCode le code du département de la structure du médecin
 * @property structureDepartmentLabel le libellé du département de la structure du médecin
 * @property oldStructureId l'ancien identifiant de la structure du médecin
 * @property registrationAuthority l'autorité d'enregistrement du médecin
 * @property activitySectorCode le code du secteur d'activité du médecin
 * @property activitySectorLabel le libellé du secteur d'activité du médecin
 * @property pharmacistsTableSectionCode le code de la section du tableau des pharmaciens du médecin
 * @property pharmacistsTableSectionLabel le libellé de la section du tableau des pharmaciens du médecin
 * @property roleCode le code du rôle du médecin
 * @property roleLabel le libellé du rôle du médecin
 * @property activityGenreCode le code du genre d'activité du médecin
 * @property activityGenreLabel le libellé du genre d'activité du médecin
 *
 */

@Entity
data class Doctor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "doctor_id")
    var id: Long = 0L,
    @SerializedName("idNational") var nationalId: Int = 0,
    @SerializedName("idTypePP") var idTypePP: Int = 0,
    @SerializedName("idPP") var idPP: Int = 0,
    @SerializedName("codeCiviliteEx") var civilCodeEx: String = "",
    @SerializedName("libelleCiviliteEx") var civilLabelEx: String = "",
    @SerializedName("codeCivilite") var civilCode: String = "",
    @SerializedName("libelleCivilite") var civilLabel: String = "",
    @SerializedName("nom") var lastName: String = "",
    @SerializedName("prenom") var firstName: String = "",
    @SerializedName("codeProfession") var professionCode: String = "",
    @SerializedName("libelleProfession") var professionLabel: String = "",
    @SerializedName("codeCategorieProfessionnelle") var professionalCategoryCode: String = "",
    @SerializedName("libelleCategorieProfessionnelle") var professionalCategoryLabel: String = "",
    @SerializedName("codeTypeSavoirFaire") var skillTypeCode: String = "",
    @SerializedName("libelleTypeSavoirFaire") var skillTypeLabel: String = "",
    @SerializedName("codeSavoirFaire") var skillCode: String = "",
    @SerializedName("libelleSavoirFaire") var skillLabel: String = "",
    @SerializedName("codeModeExercice") var practiceModeCode: String = "",
    @SerializedName("libelleModeExercice") var practiceModeLabel: String = "",
    @SerializedName("numSiretSite") var siteSiretNumber: String = "",
    @SerializedName("numSirenSite") var siteSirenNumber: String = "",
    @SerializedName("numFinessSite") var siteFinessNumber: String = "",
    @SerializedName("numFinessEtabJuridique") var legalEstablishmentFinessNumber: String = "",
    @SerializedName("idTechStructure") var techStructureId: String = "",
    @SerializedName("raisonSocialeSite") var siteCompanyName: String = "",
    @SerializedName("enseigneCommercialeSite") var siteCommercialSign: String = "",
    @SerializedName("complementDestinataireCoordStructure") var structureRecipientComplement: String = "",
    @SerializedName("complementPointGeographiqueCoordStructure") var structureGeographicPointComplement: String = "",
    @SerializedName("numVoieCoordStructure") var structureStreetNumber: String = "",
    @SerializedName("indiceRepetitionVoieCoordStructure") var structureStreetRepeatIndex: String = "",
    @SerializedName("codeTypeVoieCoordStructure") var structureStreetTypeCode: String = "",
    @SerializedName("libelleTypeVoieCoordStructure") var structureStreetTypeLabel: String = "",
    @SerializedName("libelleVoieCoordStructure") var structureStreetLabel: String = "",
    @SerializedName("mentionDistributionCoordStructure") var structureDistributionMention: String = "",
    @SerializedName("bureauCedexCoordStructure") var structureCedexOffice: String = "",
    @SerializedName("codePostalCoordStructure") var structurePostalCode: String = "",
    @SerializedName("codeCommuneCoordStructure") var structureCommuneCode: String = "",
    @SerializedName("libelleCommuneCoordStructure") var structureCommuneLabel: String = "",
    @SerializedName("codePaysCoordStructure") var structureCountryCode: String = "",
    @SerializedName("libellePaysCoordStructure") var structureCountryLabel: String = "",
    @SerializedName("telephoneCoordStructure") var structurePhoneNumber: String = "",
    @SerializedName("telephone2CoordStructure") var structurePhoneNumber2: String = "",
    @SerializedName("telecopieCoordStructure") var structureFaxNumber: String = "",
    @SerializedName("adresseMailCoordStructure") var structureEmailAddress: String = "",
    @SerializedName("codeDepartementStructure") var structureDepartmentCode: String = "",
    @SerializedName("libelleDepartementStructure") var structureDepartmentLabel: String = "",
    @SerializedName("ancienIdStructure") var oldStructureId: String = "",
    @SerializedName("autoriteEnregistrement") var registrationAuthority: String = "",
    @SerializedName("codeSecteurActivite") var activitySectorCode: String = "",
    @SerializedName("libelleSecteurActivite") var activitySectorLabel: String = "",
    @SerializedName("codeSectionTableauPharmaciens") var pharmacistsTableSectionCode: String = "",
    @SerializedName("libelleSectionTableauPharmaciens") var pharmacistsTableSectionLabel: String = "",
    @SerializedName("codeRole") var roleCode: String = "",
    @SerializedName("libelleRole") var roleLabel: String = "",
    @SerializedName("codeGenreActivite") var activityGenreCode: String = "",
    @SerializedName("libelleGenreActivite") var activityGenreLabel: String = ""
)
