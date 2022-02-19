package com.task.noteapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.task.noteapp.CoroutineTestRule
import com.task.noteapp.MockNoteUtils
import com.task.noteapp.data.local.dao.NoteDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
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
    fun getAllNotes() {
        coroutineTestRule.testDispatcher.runBlockingTest {
            val mockData = MockNoteUtils.getMockNote()
        }
    }
    
}
