package fr.medicapp.medicapp.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GenericGroup(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "generic_group_id")
    val id: Long = 0L,

    @SerializedName("GenericGroupId")
    var genericGroupId: Long = 0L,

    @SerializedName("GenericGroupLabel")
    var genericGroupLabel: String = "",

    @SerializedName("CISCode")
    var cisCode: Long = 0L,

    @SerializedName("GenericType")
    var genericType: Int = 0,

    @SerializedName("GenericName")
    var genericName: String = "",

    @SerializedName("SortNumber")
    var sortNumber: Int? = null,

    @ColumnInfo(name = "medication_id")
    var medicationId: Long = 0L
)
