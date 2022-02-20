package com.task.noteapp.note.ui.components.addeditnote

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.task.noteapp.note.ui.nav.Screen
import com.task.noteapp.note.viewmodel.AddEditNoteViewModel
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun AddEditNoteScreen(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {

    val titleState = viewModel.noteTitle.value
    val descriptionState = viewModel.noteDescription.value

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()
    val localFocusManager = LocalFocusManager.current
    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.addNewNote()
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = "Note Added"
                        )
                    }
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
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.enteredTitle(it)
                },
                onFocusChange = {
                    viewModel.changeTitleFocus(it)
                },
                isHintvisible = descriptionState.isHintVisible,
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onNext = {
                        localFocusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                imeAction = ImeAction.Next,
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = descriptionState.text,
                hint = descriptionState.hint,
                onValueChange = {
                    viewModel.enteredDescription(it)
                },
                onFocusChange = {
                    viewModel.changeDescriptionFocus(it)
                },
                isHintvisible = descriptionState.isHintVisible,
                textStyle = MaterialTheme.typography.body1,
                keyboardActions = KeyboardActions(
                    onDone = {
                        localFocusManager.clearFocus()
                        localSoftwareKeyboardController?.hide()
                    }
                ),
                imeAction = ImeAction.Done,
                modifier = Modifier.fillMaxHeight()

            )
        }

    }

}


/*
@Preview(showSystemUi = true)
@Composable
fun AddEditNoteScreenPreview() {
    AddEditNoteScreen(navController = rememberNavController())
}
*/
