package com.task.noteapp.note.ui.components.addeditnote

data class NoteTextFieldState(
    var text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
    val isUpdateTag : Boolean = true
)