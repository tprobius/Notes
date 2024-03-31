package com.tprobius.notes.data.repository

import com.tprobius.notes.data.database.NotesDao
import com.tprobius.notes.data.entities.NoteDbEntity
import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.repository.NotesDatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NotesDatabaseRepositoryImpl(
    private val notesDao: NotesDao,
    private val dispatcher: CoroutineDispatcher
) : NotesDatabaseRepository {

    override suspend fun getAllNotes(): List<Note> {
        return withContext(dispatcher) {
            notesDao.getAllNotes().map { it.mapToNote() }
        }
    }

    override suspend fun getFavoriteNotes(): List<Note> {
        return withContext(dispatcher) {
            notesDao.getFavoriteNotes().map { it.mapToNote() }
        }
    }

    override suspend fun getNoteById(id: Long): Note {
        return withContext(dispatcher) {
            notesDao.getNoteById(id).mapToNote()
        }
    }

    override suspend fun addNewNote(note: Note) {
        return withContext(dispatcher) {
            notesDao.addNewNote(NoteDbEntity.mapToNoteDbEntity(note))
        }
    }

    override suspend fun setNoteFavorite(id: Long, isFavorite: Boolean) {
        return withContext(dispatcher) {
            notesDao.setNoteFavorite(id, isFavorite)
        }
    }

    override suspend fun deleteNote(note: Note) {
        return withContext(dispatcher) {
            notesDao.deleteNote(NoteDbEntity.mapToNoteDbEntity(note))
        }
    }
}