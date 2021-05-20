package com.sid1818713.plannerapp.notedata.repository

import androidx.lifecycle.LiveData
import com.sid1818713.plannerapp.notedata.model.Note
import com.sid1818713.plannerapp.notedata.NoteDao

class NoteRepository(private val noteDao: NoteDao) {
    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote (note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }
}