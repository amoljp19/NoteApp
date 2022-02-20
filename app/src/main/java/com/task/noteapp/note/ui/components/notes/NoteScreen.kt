package com.task.noteapp.note.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.task.noteapp.data.local.model.Note
import com.task.noteapp.data.repository.DummyNoteRepository
import com.task.noteapp.note.ui.nav.Screen
import com.task.noteapp.note.viewmodel.NoteViewModel
import kotlinx.coroutines.launch

@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NoteViewModel = hiltViewModel()
) {

   // val state = DummyNoteRepository.getDummyNotes()
    val state = viewModel.notesLiveData.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val deletedItem = remember { mutableStateListOf<Note>() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(
                        route = Screen.AddEditNoteScreen.route
                    )
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
        scaffoldState = scaffoldState
    ) {

        LazyColumn(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            state?.let {

                itemsIndexed(
                    items = state,
                    itemContent = { _, note ->
                        AnimatedVisibility(
                            visible = !deletedItem.contains(note),
                            enter = expandVertically(),
                            exit = shrinkVertically(animationSpec = tween(durationMillis = 1000))
                        ) {
                            NoteItem(
                                note,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        navController.navigate(
                                            route = Screen.AddEditNoteScreen.route
                                        )
                                    },
                                onDeleteClick = {
                                    viewModel.delete(note)
                                    deletedItem.add(note)
                                    scope.launch {
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            message = "Note deleted"
                                        )
                                    }
                                }
                            )
                        }
                    }
                )
            }
        }

    }


}

@Preview(showSystemUi = true)
@Composable
fun NoteScreenPreview() {
    NotesScreen(navController = rememberNavController())
}