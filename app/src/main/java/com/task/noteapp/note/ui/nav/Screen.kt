package com.task.noteapp.note.ui.nav

const val ADD_EDIT_ARGUMENT_KEY1 = "id"
const val ADD_EDIT_ARGUMENT_KEY2 = "title"
const val ADD_EDIT_ARGUMENT_KEY3 = "description"

sealed class Screen(val route : String){
    object NotesScreen: Screen("notes_screen")
    object AddEditNoteScreen: Screen("add_edit_note_screen/{$ADD_EDIT_ARGUMENT_KEY1}/{$ADD_EDIT_ARGUMENT_KEY2}/{$ADD_EDIT_ARGUMENT_KEY3}"){
        fun passArguments(
            id: Int,
            title: String,
            description:String
        ) : String{
          return "add_edit_note_screen/$id/$title/$description"
        }
    }
}
