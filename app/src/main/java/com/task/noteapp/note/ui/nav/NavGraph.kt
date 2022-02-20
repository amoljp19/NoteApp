package com.task.noteapp.note.ui.nav

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            route = Screen.AddEditNoteScreen.route,
            arguments = listOf(
                navArgument(ADD_EDIT_ARGUMENT_KEY1){
                    type = NavType.IntType
                },
                navArgument(ADD_EDIT_ARGUMENT_KEY2){
                    type = NavType.StringType
                },
                navArgument(ADD_EDIT_ARGUMENT_KEY3){
                    type = NavType.StringType
                }
            )
        ) {
            Log.d("args", it.arguments?.getInt(ADD_EDIT_ARGUMENT_KEY1).toString())
            Log.d("args", it.arguments?.getString(ADD_EDIT_ARGUMENT_KEY2).toString())
            Log.d("args", it.arguments?.getString(ADD_EDIT_ARGUMENT_KEY3).toString())
            val note =
            AddEditNoteScreen(navController)
        }
    }
}