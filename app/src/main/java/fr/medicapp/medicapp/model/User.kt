package fr.medicapp.medicapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Long = 0L,
    val lastName: String = "",
    val firstName: String = "",
    val birthday: LocalDate = LocalDate.now(),
    val gender: String = "",
    val pregnant: Boolean = false,
    val allergies: MutableList<String> = mutableListOf()
)
