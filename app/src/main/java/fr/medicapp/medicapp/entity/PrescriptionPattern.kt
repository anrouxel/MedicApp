package fr.medicapp.medicapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * Table nommée PrescriptionPattern.
 * */
@Entity(tableName = "PrescriptionPattern")
data class PrescriptionPattern(
    @PrimaryKey val sentence: String
)