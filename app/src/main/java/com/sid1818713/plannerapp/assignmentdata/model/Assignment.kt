package com.sid1818713.plannerapp.assignmentdata.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assignment_database")
data class Assignment (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val moduleNumber: String,
    val assignmentNumber: String,
    val assignmentDetails: String,
    val assignmentDue: String
)