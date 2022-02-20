package com.task.noteapp.data.local.dao

import androidx.room.*
import com.task.noteapp.data.local.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note): Long

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM ${Note.TABLE_NAME} WHERE id = :id")
    suspend fun getNoteById(id: Int): Note?

    @Query("select * from ${Note.TABLE_NAME}")
    fun getAllNotes(): Flow<List<Note>>
}