package fr.medicapp.medicapp.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PrescriptionDispensingConditions")
data class PrescriptionDispensingConditionsEntity(

    /**
     * L'identifiant unique des conditions de délivrance de l'ordonnance.
     */
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: String,

    /**
     * Le code CIS
     */
    @ColumnInfo(name = "CISCode")
    val cisCode: String,

    /**
     * Condition de dispense de l'ordonnance
     */
    @ColumnInfo(name = "PrescriptionDispensingCondition")
    val prescriptionDispensingCondition: String,

    /**
     * id medicament
     */
    @ColumnInfo(name = "MedicationId")
    val medicationId: String
)
