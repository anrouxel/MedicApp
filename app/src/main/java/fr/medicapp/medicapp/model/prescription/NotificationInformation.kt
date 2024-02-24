package fr.medicapp.medicapp.model.prescription

import androidx.compose.runtime.mutableStateListOf
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.DayOfWeek

@Entity
data class NotificationInformation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "notification_id")
    val id: Long = 0L,

    var active: Boolean = true,

    var days: MutableList<DayOfWeek> = mutableStateListOf(),

    @ColumnInfo(name = "prescription_id")
    val prescriptionId: Long = 0L
)