package com.task.noteapp.note.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.task.noteapp.data.local.model.Note
import com.task.noteapp.data.repository.DummyNoteRepository
import com.task.noteapp.data.repository.NoteRepository
import com.task.noteapp.note.ui.components.addeditnote.NoteTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    private val _notesLiveData = MutableLiveData<List<Note>>()

    val notesLiveData: LiveData<List<Note>> = _notesLiveData

    init {
        getNotes()
    }

//    suspend fun insertNote(note: Note) {
//        noteRepository.insertNote(note)
//    }
//
//    suspend fun updateNote(note: Note) {
//        noteRepository.updateNote(note)
//    }

    fun getAllNotes(): Flow<List<Note>> {
        return noteRepository.getAllNotes()
    }

    fun getNotes() {
        viewModelScope.launch {
            getAllNotes().collect {
                _notesLiveData.value = it
            }
        }
    }

    fun delete(note: Note){
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }
    }

}
