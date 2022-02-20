package com.task.noteapp.note.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.task.noteapp.note.ui.components.NotesScreen

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
            NotesScreen()
        }
    }
}