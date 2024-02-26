package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.medicapp.medicapp.model.OptionDialog
import java.time.LocalDate

@Entity
data class MedicationInformation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "medication_id")
    val id: Long = 0L,

    var cisCode: Long = 0L,

    var name: String = "",

    var pharmaceuticalForm: String = "",

    var administrationRoutes: MutableList<String> = mutableListOf(),

    var marketingAuthorizationStatus: String = "",

    var marketingAuthorizationProcedureType: String = "",

    var commercializationStatus: String = "",

    var marketingAuthorizationDate: LocalDate? = null,

    var bdmStatus: String = "",

    var europeanAuthorizationNumber: String = "",

    var holders: MutableList<String> = mutableListOf(),

    var enhancedMonitoring: Boolean? = null,
) {
    override fun toString(): String {
        return name
    }

    fun toOptionDialog(): OptionDialog {
        return OptionDialog(
            id,
            name
        )
    }
}
