package com.sid1818713.plannerapp.notedata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Note>>
}