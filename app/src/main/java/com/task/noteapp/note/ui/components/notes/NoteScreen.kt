package com.task.noteapp.note.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NotesScreen(
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item() {
            NoteItem(
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NoteScreenPreview() {
    NotesScreen()
}