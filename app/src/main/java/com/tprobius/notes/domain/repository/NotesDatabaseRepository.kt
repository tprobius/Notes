package com.tprobius.notes.domain.repository

import com.tprobius.notes.domain.model.Note

interface NotesDatabaseRepository {

    suspend fun getAllNotes(): List<Note>

    suspend fun getFavoriteNotes(): List<Note>

    suspend fun getNoteById(id: Long): Note

    suspend fun addNewNote(note: Note)

    suspend fun setNoteFavorite(id: Long, isFavorite: Boolean)

    suspend fun deleteNote(note: Note)
}