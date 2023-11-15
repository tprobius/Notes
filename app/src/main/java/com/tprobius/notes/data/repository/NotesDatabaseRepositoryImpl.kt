package com.tprobius.notes.data.repository

import com.tprobius.notes.data.database.NotesDao
import com.tprobius.notes.data.model.NoteDbEntity
import com.tprobius.notes.domain.entities.Note
import com.tprobius.notes.domain.repository.NotesDatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NotesDatabaseRepositoryImpl(
    private val notesDao: NotesDao,
    private val dispatcher: CoroutineDispatcher
) : NotesDatabaseRepository {
    override suspend fun getAllNotes(): Flow<List<Note>> {
        return withContext(dispatcher) {
            flow {
                notesDao.getAllNotes().map { list ->
                    list.forEach { note ->
                        note.mapToNote()
                    }
                }
            }
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

    override suspend fun deleteNote(note: Note) {
        return withContext(dispatcher) {
            notesDao.deleteNote(NoteDbEntity.mapToNoteDbEntity(note))
        }
    }
}