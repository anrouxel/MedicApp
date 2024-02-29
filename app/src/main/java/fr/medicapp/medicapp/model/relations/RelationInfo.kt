package fr.medicapp.medicapp.model.relations

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RelationInfo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "relation_info_id")
    val id: Long = 0L,

    val substance: String = "",
    val com: String = ""
)