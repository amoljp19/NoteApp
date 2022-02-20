package com.task.noteapp.note.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.task.noteapp.note.ui.components.NotesScreen
import com.task.noteapp.note.ui.components.addeditnote.AddEditNoteScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.NotesScreen.route
    ) {
        composable(
            route = Screen.NotesScreen.route
        ) {
            NotesScreen(navController)
        }

        composable(
            route = Screen.AddEditNoteScreen.route
        ) {
            AddEditNoteScreen()
        }
    }
}