package fr.medicapp.medicapp.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MedicationCompositions")
data class MedicationCompositionsEntity(

    /**
     * L'identifiant unique de la composition du médicament.
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
     * Désignation de l'element pharmaceutique
     */
    @ColumnInfo(name = "PharmaceuticalElementDesignation")
    val pharmaceuticalElementDesignation: String,

    /**
     * Code de la substance
     */
    @ColumnInfo(name = "SubstanceCode")
    val substanceCode: String,

    /**
     * Nom de la substance
     */
    @ColumnInfo(name = "SubstanceName")
    val substanceName: String,

    /**
     * Dosage de la substance
     */
    @ColumnInfo(name = "SubstanceDosage")
    val substanceDosage: String,

    /**
     * Reference du dosage
     */
    @ColumnInfo(name = "DosageReference")
    val dosageReference: String,

    /**
     * Nature du composant
     */
    @ColumnInfo(name = "ComponentNature")
    val componentNature: String,

    /**
     * nombre de lien
     */
    @ColumnInfo(name = "LinkNumber")
    val linkNumber: Int,

    /**
     * id du médicament
     */
    @ColumnInfo(name = "MedicationId")
    val medicationId: String
)
