package com.task.noteapp.note.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.local.model.Note
import com.task.noteapp.data.repository.NoteRepository
import com.task.noteapp.note.ui.components.addeditnote.NoteTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter title..."
        )
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteDescription = mutableStateOf(
        NoteTextFieldState(
        hint = "Enter description..."
    )
    )
    val noteDescription: State<NoteTextFieldState> = _noteDescription

    private var currentNoteId: Int = -1


    init {
        savedStateHandle.get<Int>("id")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    noteRepository.getNoteById(noteId).also { note ->
                        currentNoteId = note.id
                        _noteTitle.value = noteTitle.value.copy(
                            text = note.title,
                            isHintVisible = false
                        )
                        _noteDescription.value = noteDescription.value.copy(
                            text = note.description,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }

    fun addNewNote(){
        viewModelScope.launch {
            noteRepository.insertNote(
                Note(
                    id = currentNoteId,
                    title = noteTitle.value.text,
                    description = noteDescription.value.text,
                    date = System.currentTimeMillis(),
                    tag = ""
                )
            )
        }
    }

    fun enteredTitle(title : String){
        _noteTitle.value = noteTitle.value.copy(
            text = title
        )
    }

    fun changeTitleFocus(focusState: FocusState){
        _noteTitle.value = noteTitle.value.copy(
            isHintVisible = !focusState.isFocused
                    && noteTitle.value.text.isBlank()
        )
    }


    fun enteredDescription(description : String){
        _noteDescription.value = noteDescription.value.copy(
            text = description
        )
    }

    fun changeDescriptionFocus(focusState: FocusState){
        _noteDescription.value = noteDescription.value.copy(
            isHintVisible = !focusState.isFocused
                    && noteDescription.value.text.isBlank()
        )
    }

}
