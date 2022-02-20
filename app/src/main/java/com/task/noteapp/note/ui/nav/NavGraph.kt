package com.task.noteapp.note.ui.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.task.noteapp.note.ui.components.NotesScreen
import com.task.noteapp.note.ui.components.addeditnote.AddEditNoteScreen

@ExperimentalComposeUiApi
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
            route = Screen.AddEditNoteScreen.route,
        ) {
            AddEditNoteScreen(navController)
        }
    }
}