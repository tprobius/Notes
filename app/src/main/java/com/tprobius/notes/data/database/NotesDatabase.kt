package com.tprobius.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tprobius.notes.data.entities.NoteDbEntity

@Database(entities = [NoteDbEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract val notesDao: NotesDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}