package com.task.noteapp.data.repository

import com.task.noteapp.data.local.model.Note

object DummyNoteRepository {

    internal fun getDummyNote(): Note {
        return Note(
            id = 1,
            title = "title 1",
            description = "description 1",
            date = 0L,
            tag = "tag 1"
        )
    }

    internal fun getDummyNotes() : List<Note> {
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