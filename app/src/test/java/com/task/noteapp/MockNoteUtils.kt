package com.task.noteapp

import com.task.noteapp.data.local.model.Note

object MockNoteUtils {

    internal fun getMockNote(): Note {
        return Note(
            id = 1,
            title = "title",
            description = "description",
            date = 0L,
            tag = "tag"
        )
    }
}