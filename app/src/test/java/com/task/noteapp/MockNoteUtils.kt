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

    internal fun getMockNotes(): List<Note> {
        return listOf(
            Note(
                id = 1,
                title = "title1",
                description = "description1",
                date = 1L,
                tag = "tag1"
            ),
            Note(
                id = 2,
                title = "title2",
                description = "descriptio2",
                date = 2L,
                tag = "tag2"
            ),
            Note(
                id = 3,
                title = "title3",
                description = "descriptio3",
                date = 3L,
                tag = "tag3"
            )
        )
    }
}