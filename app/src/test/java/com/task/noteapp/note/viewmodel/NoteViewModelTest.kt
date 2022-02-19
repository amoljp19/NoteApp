package com.task.noteapp.note.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.noteapp.CoroutineTestRule
import com.task.noteapp.MockNoteUtils
import com.task.noteapp.data.local.model.Note
import com.task.noteapp.data.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class NoteViewModelTest {

    private lateinit var noteViewModel: NoteViewModel

    @Mock
    private lateinit var noteRepository: NoteRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        noteViewModel = NoteViewModel(noteRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun insertNoteTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val mockNote = MockNoteUtils.getMockNote()
            noteViewModel.insertNote(mockNote)

            val argumentCaptor = argumentCaptor<Note>()
            verify(noteRepository).insertNote(argumentCaptor.capture())
            Assert.assertEquals(mockNote.title, argumentCaptor.firstValue.title);

        }

    }


    @ExperimentalCoroutinesApi
    @Test
    fun updateNoteTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val mockNote = MockNoteUtils.getMockNote()
            noteViewModel.updateNote(mockNote.copy(tag = "updated tag"))

            val argumentCaptor = argumentCaptor<Note>()
            verify(noteRepository).updateNote(argumentCaptor.capture())
            Assert.assertEquals("updated tag", argumentCaptor.firstValue.tag);
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deleteNoteTest() {

        val mockNote = MockNoteUtils.getMockNote()
        noteViewModel.deleteNote(mockNote)

        val argumentCaptor = argumentCaptor<Note>()
        verify(noteRepository).deleteNote(argumentCaptor.capture())
        Assert.assertEquals(mockNote.title, argumentCaptor.firstValue.title);

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getAllNotesTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val mockNotes = MockNoteUtils.getMockNotes()

            whenever(noteRepository.getAllNotes()) doReturn flowOf(
                mockNotes
            )

            val data = noteViewModel.getAllNotes()

            Assert.assertEquals(data.first().get(0), mockNotes.get(0))
            Assert.assertEquals(data.first().get(1), mockNotes.get(1))
            Assert.assertEquals(data.first().get(2), mockNotes.get(2))
        }
    }

}