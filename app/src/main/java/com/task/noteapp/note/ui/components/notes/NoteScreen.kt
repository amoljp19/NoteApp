package com.task.noteapp.note.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.task.noteapp.note.ui.nav.Screen

@Composable
fun NotesScreen(
    navController: NavController
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item() {
            NoteItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(route = Screen.AddEditNoteScreen.route)
                    }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NoteScreenPreview() {
    NotesScreen(navController = rememberNavController() )
}