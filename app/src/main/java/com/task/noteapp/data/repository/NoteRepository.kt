package com.task.noteapp.data.repository

import com.task.noteapp.data.local.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    fun deleteNote(note: Note)
    fun getAllNotes(): Flow<List<Note>>
}