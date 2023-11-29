package fr.medicapp.medicapp.entity

import androidx.room.Entity

@Entity(tableName = "SentenceData")
data class SentenceData(
    val sentence: String,
    val labels: List<String>
)