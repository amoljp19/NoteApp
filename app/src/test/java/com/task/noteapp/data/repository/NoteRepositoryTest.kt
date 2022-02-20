package com.task.noteapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.noteapp.CoroutineTestRule
import com.task.noteapp.MockNoteUtils
import com.task.noteapp.data.local.dao.NoteDao
import com.task.noteapp.data.local.model.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class NoteRepositoryTest {
    private lateinit var noteRepository: NoteRepository

    @Mock
    private lateinit var noteDao: NoteDao

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        noteRepository = DefaultNoteRepository(noteDao)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertNoteTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val mockNote = MockNoteUtils.getMockNote()
            noteRepository.insertNote(mockNote)

            val argumentCaptor = argumentCaptor<Note>()
            verify(noteDao).insertNote(argumentCaptor.capture())
            assertEquals(mockNote.title, argumentCaptor.firstValue.title);

        }

    }


    @ExperimentalCoroutinesApi
    @Test
    fun updateNoteTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val mockNote = MockNoteUtils.getMockNote()
            noteRepository.updateNote(mockNote.copy(tag = "updated tag"))

            val argumentCaptor = argumentCaptor<Note>()
            verify(noteDao).updateNote(argumentCaptor.capture())
            assertEquals("updated tag", argumentCaptor.firstValue.tag);
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteNoteTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val mockNote = MockNoteUtils.getMockNote()
            noteRepository.deleteNote(mockNote)

            val argumentCaptor = argumentCaptor<Note>()
            verify(noteDao).deleteNote(argumentCaptor.capture())
            assertEquals(mockNote.title, argumentCaptor.firstValue.title);
        }

    }

    @Test
    fun getNoteByIdTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val mockNotes = MockNoteUtils.getMockNotes()

            whenever(noteDao.getNoteById(1)) doReturn mockNotes.get(1)


            val data = noteRepository.getNoteById(1)

            assertEquals(data, mockNotes.get(1))
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllNotesTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val mockNotes = MockNoteUtils.getMockNotes()

            whenever(noteDao.getAllNotes()) doReturn flowOf(
                mockNotes
            )

            val data = noteRepository.getAllNotes()

            assertEquals(data.first().get(0), mockNotes.get(0))
            assertEquals(data.first().get(1), mockNotes.get(1))
            assertEquals(data.first().get(2), mockNotes.get(2))
        }
    }
}
