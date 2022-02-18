package com.task.noteapp.data.repository

import com.task.noteapp.data.local.dao.NoteDao
import com.task.noteapp.data.local.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultNoteRepository @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    
    override suspend fun insertNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun updateNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun getAllNotes(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }
}