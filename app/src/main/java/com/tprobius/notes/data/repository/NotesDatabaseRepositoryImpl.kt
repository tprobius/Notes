package com.tprobius.notes.data.repository

import com.tprobius.notes.data.database.NotesDao
import com.tprobius.notes.data.entities.NoteDbEntity
import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.repository.NotesDatabaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class NotesDatabaseRepositoryImpl(
    private val notesDao: NotesDao,
    private val dispatcher: CoroutineDispatcher
) : NotesDatabaseRepository {
    override suspend fun getAllNotes(): Flow<List<Note>> {
        return flow {
            emit(notesDao.getAllNotes().map {
                it.mapToNote()
            })
        }.flowOn(dispatcher)
    }

    override suspend fun getFavoriteNotes(): Flow<List<Note>> {
        return flow {
            emit(notesDao.getFavoriteNotes().map {
                it.mapToNote()
            })
        }.flowOn(dispatcher)
    }

    override suspend fun getNoteById(id: Long): Flow<Note> {
        return flow {
            emit(notesDao.getNoteById(id).mapToNote())
        }.flowOn(dispatcher)
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