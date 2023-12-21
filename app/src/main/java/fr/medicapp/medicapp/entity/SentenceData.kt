package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table nommée SentenceData.
 * */
@Entity(tableName = "SentenceData")
data class SentenceData(
    @PrimaryKey val sentence: String,
    val labels: List<String>
)