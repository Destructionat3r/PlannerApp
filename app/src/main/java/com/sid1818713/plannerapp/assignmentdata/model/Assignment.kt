package com.sid1818713.plannerapp.assignmentdata.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "assignment_database")
data class Assignment (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val moduleNumber: String,
    val assignmentNumber: String,
    val assignmentDetails: String,
    val assignmentDue: String
): Parcelable