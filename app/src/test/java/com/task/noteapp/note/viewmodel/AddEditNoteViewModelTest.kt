package com.task.noteapp.note.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.focus.FocusState
import androidx.lifecycle.SavedStateHandle
import com.task.noteapp.CoroutineTestRule
import com.task.noteapp.MockNoteUtils
import com.task.noteapp.data.local.model.Note
import com.task.noteapp.data.repository.NoteRepository
import com.task.noteapp.note.ui.components.addeditnote.NoteTextFieldState
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

// ToDo need to spent more time on all condition, for timebeing only success case handled here

class AddEditNoteViewModelTest {

    private lateinit var addEditNoteViewModel: AddEditNoteViewModel

    @Mock
    private lateinit var noteRepository: NoteRepository

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    @Mock
    private lateinit var focusState: FocusState


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


    // ToDo need to spent more time on this test case, for timebeing passing
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addNewNoteTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {

            //Given
            val mockNote = MockNoteUtils.getMockNote()

            whenever(noteRepository.insertNote(mockNote)).then {
                NoteTextFieldState(mockNote.title, "", false, false)
            }

            addEditNoteViewModel.addNewNote()



            Assert.assertEquals("", addEditNoteViewModel.noteTitle.value.text);

        }
    }


    // ToDo need to spent more time on this test case, for timebeing passing
    @ExperimentalCoroutinesApi
    @Test
    fun updateTest() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val mockNote = MockNoteUtils.getMockNote()
            addEditNoteViewModel.updateNote()

            val argumentCaptor = argumentCaptor<Note>()
            verify(noteRepository).updateNote(argumentCaptor.capture())
            Assert.assertEquals("", argumentCaptor.firstValue.tag);
        }

    }


    @Test
    fun enteredTitleTest() {

        val note = NoteTextFieldState("abc", "", false, false)

        addEditNoteViewModel.enteredTitle(note.text)

        Assert.assertEquals("abc", addEditNoteViewModel.noteTitle.value.text)
    }

    @Test
    fun enteredDescriptionTest() {

        val note = NoteTextFieldState("abc", "", false, false)


        addEditNoteViewModel.enteredDescription(note.text)

        Assert.assertEquals("abc", addEditNoteViewModel.noteDescription.value.text)
    }

    @Test
    fun changeTitleFocusTest() {
        NoteTextFieldState("", "", false, false)

        whenever(focusState.isFocused) doReturn false

        addEditNoteViewModel.changeTitleFocus(focusState)

        Assert.assertEquals(true, addEditNoteViewModel.noteTitle.value.isHintVisible)

    }

    @Test
    fun changeDescriptionFocusTest() {
        NoteTextFieldState("", "", false, false)

        whenever(focusState.isFocused) doReturn false

        addEditNoteViewModel.changeDescriptionFocus(focusState)

        Assert.assertEquals(true, addEditNoteViewModel.noteTitle.value.isHintVisible)

    }
}