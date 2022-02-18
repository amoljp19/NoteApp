package com.task.noteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Note.TABLE_NAME)
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var description: String,
    var date: Long,
    var tag:String
) {
    companion object{
        const val TABLE_NAME = "notes_table"
    }
}