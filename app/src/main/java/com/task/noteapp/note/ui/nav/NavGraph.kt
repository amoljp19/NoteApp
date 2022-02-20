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
            route = Screen.AddEditNoteScreen.route  +
                    "?noteId={noteId}&noteTag={noteTag}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "noteTag"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val tag = it.arguments?.getString("noteTag") ?: ""
            AddEditNoteScreen(
                navController,
                noteTag = tag
            )
        }
    }
}