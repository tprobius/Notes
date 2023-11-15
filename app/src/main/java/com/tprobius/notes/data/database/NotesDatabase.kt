package com.tprobius.notes.data.database

abstract class NotesDatabase {
    abstract val notesDao: NotesDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}