package com.task.noteapp.note.ui.components.addeditnote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.task.noteapp.note.ui.nav.Screen
import com.task.noteapp.note.viewmodel.NoteViewModel

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    viewModel : NoteViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    //viewModel.insertNote(note)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Save note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .clickable {
                    navController.navigate(route = Screen.NotesScreen.route)
                }
        ) {

            TransparentHintTextField(
                text = "title",
                hint = "Enter title here",
                isHintvisible = false,
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onNext = {

                    }
                ),
                imeAction = ImeAction.Next,
                textStyle = MaterialTheme.typography.h5,
                onFocusChange = {},
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = "description",
                hint = "Enter description here",
                isHintvisible = false,
                textStyle = MaterialTheme.typography.body1,
                keyboardActions = KeyboardActions(
                    onDone = {

                    }
                ),
                imeAction = ImeAction.Done,
                modifier = Modifier.fillMaxHeight(),
                onFocusChange = {},
                onValueChange = {}
            )
        }

    }

}


@Preview(showSystemUi = true)
@Composable
fun AddEditNoteScreenPreview() {
    AddEditNoteScreen(navController = rememberNavController())
}
