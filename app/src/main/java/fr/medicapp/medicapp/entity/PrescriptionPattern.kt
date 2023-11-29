package fr.medicapp.medicapp.entity

import androidx.room.Entity

@Entity(tableName = "PrescriptionPattern")
data class PrescriptionPattern(
    val sentence: String
)