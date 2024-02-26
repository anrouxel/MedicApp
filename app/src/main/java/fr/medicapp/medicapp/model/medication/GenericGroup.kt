package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenericGroup(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "generic_group_id")
    val id: Long = 0L,

    var genericGroupId: Long = 0L,

    var genericGroupLabel: String = "",

    var cisCode: Long = 0L,

    var genericType: Int = 0,

    var genericName: String = "",

    var sortNumber: Int? = null,

    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L
)
