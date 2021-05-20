package com.sid1818713.plannerapp.moduledata.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "module_table")
data class Module (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val moduleNumber: String,
    val module: String
): Parcelable