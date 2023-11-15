package com.tprobius.notes.domain.repository

import com.tprobius.notes.domain.entities.Note
import kotlinx.coroutines.flow.Flow

interface NotesDatabaseRepository {
    suspend fun getAllNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: Long): Note

    suspend fun addNewNote(note: Note)

    suspend fun deleteNote(note: Note)
}