package fr.medicapp.medicapp.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey


@Entity(tableName = "GenericGroups", foreignKeys =
arrayOf(ForeignKey(entity = MedicationEntity::class, parentColumns = ["Id"], childColumns = ["MedicationId"]))
)
data class GenericGroupsEntity(

    /**
     * L'identifiant unique du groupe générique.
     */
    @PrimaryKey
    @ColumnInfo(name = "Id")
    val id: String,

    /**
     * L'id du groupe générique.
     */

    @ColumnInfo(name = "GenericGroupId")
    val genericGroupId: String,


    /**
     * Le nom du groupe générique.
     */
    @ColumnInfo(name = "GenericGroupLabel")
    val label: String,

    /**
     * Le code CIS du médicament.
     */
    @ColumnInfo(name = "CISCode")
    val cisCode: String,

    /**
     * Le type générique du médicament.
     */
    @ColumnInfo(name = "GenericType")
    val type: Int,

    /**
     * le nom générique du médicament.
     */
    @ColumnInfo(name = "GenericName")
    val genericName: String,

    /**
     * sort number
     */
    @ColumnInfo(name = "SortNumber")
    val sortNumber: Int,

    /**
     * id du médicament
     */
    @ColumnInfo(name = "MedicationId")
    val medicationId: String
)
