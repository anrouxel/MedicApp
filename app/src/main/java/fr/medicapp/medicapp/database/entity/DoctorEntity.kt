package fr.medicapp.medicapp.database.entity

import fr.medicapp.medicapp.database.converter.EntityToModelMapper
import fr.medicapp.medicapp.model.Doctor
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class DoctorEntity(
    @Id
    var nationalId: Long = 0L,
    var idTypePP: Int = 0,
    var idPP: Int = 0,
    var civilCodeEx: String = "",
    var civilLabelEx: String = "",
    var civilCode: String = "",
    var civilLabel: String = "",
    var lastName: String = "",
    var firstName: String = "",
    var professionCode: String = "",
    var professionLabel: String = "",
    var professionalCategoryCode: String = "",
    var professionalCategoryLabel: String = "",
    var skillTypeCode: String = "",
    var skillTypeLabel: String = "",
    var skillCode: String = "",
    var skillLabel: String = "",
    var practiceModeCode: String = "",
    var practiceModeLabel: String = "",
    var siteSiretNumber: String = "",
    var siteSirenNumber: String = "",
    var siteFinessNumber: String = "",
    var legalEstablishmentFinessNumber: String = "",
    var techStructureId: String = "",
    var siteCompanyName: String = "",
    var siteCommercialSign: String = "",
    var structureRecipientComplement: String = "",
    var structureGeographicPointComplement: String = "",
    var structureStreetNumber: String = "",
    var structureStreetRepeatIndex: String = "",
    var structureStreetTypeCode: String = "",
    var structureStreetTypeLabel: String = "",
    var structureStreetLabel: String = "",
    var structureDistributionMention: String = "",
    var structureCedexOffice: String = "",
    var structurePostalCode: String = "",
    var structureCommuneCode: String = "",
    var structureCommuneLabel: String = "",
    var structureCountryCode: String = "",
    var structureCountryLabel: String = "",
    var structurePhoneNumber: String = "",
    var structurePhoneNumber2: String = "",
    var structureFaxNumber: String = "",
    var structureEmailAddress: String = "",
    var structureDepartmentCode: String = "",
    var structureDepartmentLabel: String = "",
    var oldStructureId: String = "",
    var registrationAuthority: String = "",
    var activitySectorCode: String = "",
    var activitySectorLabel: String = "",
    var pharmacistsTableSectionCode: String = "",
    var pharmacistsTableSectionLabel: String = "",
    var roleCode: String = "",
    var roleLabel: String = "",
    var activityGenreCode: String = "",
    var activityGenreLabel: String = ""
) : EntityToModelMapper<Doctor> {
    override fun convert(): Doctor {
        return Doctor(
            nationalId,
            idTypePP,
            idPP,
            civilCodeEx,
            civilLabelEx,
            civilCode,
            civilLabel,
            lastName,
            firstName,
            professionCode,
            professionLabel,
            professionalCategoryCode,
            professionalCategoryLabel,
            skillTypeCode,
            skillTypeLabel,
            skillCode,
            skillLabel,
            practiceModeCode,
            practiceModeLabel,
            siteSiretNumber,
            siteSirenNumber,
            siteFinessNumber,
            legalEstablishmentFinessNumber,
            techStructureId,
            siteCompanyName,
            siteCommercialSign,
            structureRecipientComplement,
            structureGeographicPointComplement,
            structureStreetNumber,
            structureStreetRepeatIndex,
            structureStreetTypeCode,
            structureStreetTypeLabel,
            structureStreetLabel,
            structureDistributionMention,
            structureCedexOffice,
            structurePostalCode,
            structureCommuneCode,
            structureCommuneLabel,
            structureCountryCode,
            structureCountryLabel,
            structurePhoneNumber,
            structurePhoneNumber2,
            structureFaxNumber,
            structureEmailAddress,
            structureDepartmentCode,
            structureDepartmentLabel,
            oldStructureId,
            registrationAuthority,
            activitySectorCode,
            activitySectorLabel,
            pharmacistsTableSectionCode,
            pharmacistsTableSectionLabel,
            roleCode,
            roleLabel,
            activityGenreCode,
            activityGenreLabel
        )
    }
}
