package com.task.noteapp.data.local.dao

import com.task.noteapp.data.local.LocalDatabase
import com.task.noteapp.data.local.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class NoteDaoTest : LocalDatabase() {

    @Test
    @Throws(InterruptedException::class)
    fun insertNoteTest() = runBlocking {

        val mockNote = getMockNote()

        mDatabase.noteDao().insertNote(mockNote)

        val dbNote = mDatabase.noteDao().getAllNotes().first().get(0)

        MatcherAssert.assertThat(dbNote, CoreMatchers.equalTo(mockNote))


    }


    private fun getMockNote(): Note {
        return Note(
            id = 1,
            title = "title",
            description = "description",
            date = 0L,
            tag = "tag"
        )
    }


}