package com.sid1818713.plannerapp.moduledata.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "module_table")
data class Module (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val module: String
)