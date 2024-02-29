package fr.medicapp.medicapp.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Interactions(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "interaction_id")
    val id: Long = 0L,
    val substance: String = "",
    val com1: String = "",
    val com2: String = "",

    @ColumnInfo(name = "relation_info_id")
    var relationInfoId: Long = 0L
)
