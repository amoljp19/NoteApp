package com.task.noteapp.note.viewmodel

import androidx.lifecycle.ViewModel
import com.task.noteapp.data.local.model.Note
import com.task.noteapp.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    val noteRepository: NoteRepository
) : ViewModel() {

    suspend fun insertNote(note: Note) {
        noteRepository.insertNote(note)
    }

    suspend fun updateNote(note: Note) {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) {
        noteRepository.deleteNote(note)
    }

    fun getAllNotes(): Flow<List<Note>> {
        return noteRepository.getAllNotes()
    }

}
