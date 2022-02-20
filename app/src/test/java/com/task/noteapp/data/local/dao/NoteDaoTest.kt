package com.task.noteapp.data.local.dao

import com.task.noteapp.MockNoteUtils
import com.task.noteapp.data.local.LocalDatabase
import com.task.noteapp.data.local.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Test

// ToDo need to spent more time on all condition, for timebeing only success case handled here
class NoteDaoTest : LocalDatabase() {

    @Test
    @Throws(InterruptedException::class)
    fun insertNoteTest() = runBlocking {

        val mockNote = MockNoteUtils.getMockNote()

        mDatabase.noteDao().insertNote(mockNote)

        val dbNote = mDatabase.noteDao().getAllNotes().first().get(0)

        MatcherAssert.assertThat(dbNote, CoreMatchers.equalTo(mockNote))

    }

    @Test
    @Throws(InterruptedException::class)
    fun updateNoteTest() = runBlocking {

        val mockNote = MockNoteUtils.getMockNote()

        mDatabase.noteDao().insertNote(mockNote)

        val updatedNote = Note(id = 1,
            title = "title",
            description = "description",
            date = 0L,
            tag = "tag updated")

        mDatabase.noteDao().updateNote(updatedNote)

        val dbNote = mDatabase.noteDao().getAllNotes().first().get(0)

        MatcherAssert.assertThat(dbNote, CoreMatchers.equalTo(updatedNote))

    }

    @Test
    fun deleteNoteTest() = runBlocking {
        val mockNote = MockNoteUtils.getMockNote()

        mDatabase.noteDao().insertNote(mockNote)

        assertEquals(mDatabase.noteDao().getAllNotes().first().size, 1)

        mDatabase.noteDao().deleteNote(mockNote)

        assertEquals(mDatabase.noteDao().getAllNotes().first().size, 0)
    }

    @Test
    fun getNoteByIdTest() = runBlocking {
        val mockNote = MockNoteUtils.getMockNote()
        mDatabase.noteDao().insertNote(mockNote)

        val note2 = Note(2, "title 2", "description 2", 0L, "tag 2")
        mDatabase.noteDao().insertNote(note2)

        assertEquals(mDatabase.noteDao().getAllNotes().first().size, 2)
        assertEquals(mDatabase.noteDao().getNoteById(1), mockNote )
        assertEquals(mDatabase.noteDao().getNoteById(2), note2 )
    }

    @Test
    fun getAllNotesTest() = runBlocking {
        val mockNote = MockNoteUtils.getMockNote()
        mDatabase.noteDao().insertNote(mockNote)

        val note2 = Note(2, "title 2", "description 2", 0L, "tag 2")
        mDatabase.noteDao().insertNote(note2)

        assertEquals(mDatabase.noteDao().getAllNotes().first().size, 2)
        assertEquals(mDatabase.noteDao().getAllNotes().first().get(0), mockNote )
        assertEquals(mDatabase.noteDao().getAllNotes().first().get(1), note2 )
    }





}