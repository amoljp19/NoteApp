package com.task.noteapp.note.ui.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NoteItem() {
    Text("Note Item")
}


@Preview(showSystemUi = true)
@Composable
fun NoteItemPreview() {
    NoteItem()
}