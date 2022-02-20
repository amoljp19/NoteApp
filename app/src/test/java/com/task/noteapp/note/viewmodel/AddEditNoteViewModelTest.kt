package com.task.noteapp.note.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.State
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.task.noteapp.CoroutineTestRule
import com.task.noteapp.MockNoteUtils
import com.task.noteapp.data.local.model.Note
import com.task.noteapp.data.repository.NoteRepository
import com.task.noteapp.note.ui.components.addeditnote.NoteTextFieldState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*

class AddEditNoteViewModelTest {

    private lateinit var addEditNoteViewModel: AddEditNoteViewModel

    @Mock
    private lateinit var noteRepository: NoteRepository

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    @Mock
    private lateinit var observer: Observer<State<NoteTextFieldState>>


    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        addEditNoteViewModel = AddEditNoteViewModel(noteRepository, savedStateHandle)
    }


    /*@OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addNewNoteTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {

            //Given
            val mockNote = MockNoteUtils.getMockNote()

            whenever(noteRepository.insertNote(mockNote)).then {
                NoteTextFieldState(mockNote.title, "", false, false)
            }

            addEditNoteViewModel.addNewNote()



            Assert.assertEquals(mockNote.title,  addEditNoteViewModel.noteTitle.value.text);

        }
    }*/




    @ExperimentalCoroutinesApi
    @Test
    fun updateTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val mockNote = MockNoteUtils.getMockNote()
            addEditNoteViewModel.updateNote()

            val argumentCaptor = argumentCaptor<Note>()
            verify(noteRepository).updateNote(argumentCaptor.capture())
            Assert.assertEquals("updated tag", argumentCaptor.firstValue.tag);
        }

    }


}