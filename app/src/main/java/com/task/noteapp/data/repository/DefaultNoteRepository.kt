package com.task.noteapp.data.repository

import com.task.noteapp.data.local.dao.NoteDao
import com.task.noteapp.data.local.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultNoteRepository @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {

    override suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }
}