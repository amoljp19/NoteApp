package com.task.noteapp.note.ui.components.addeditnote

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
    val isUpdateTag : Boolean = true
)