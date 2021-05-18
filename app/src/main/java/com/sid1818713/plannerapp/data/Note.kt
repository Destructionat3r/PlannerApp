package com.sid1818713.plannerapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val noteTitle: String,
    val noteContents: String
)