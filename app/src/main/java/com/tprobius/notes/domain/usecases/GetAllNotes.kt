package com.tprobius.notes.domain.usecases

import com.tprobius.notes.domain.entities.Note
import com.tprobius.notes.domain.repository.NotesDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotes(
    private val notesDatabaseRepository: NotesDatabaseRepository
) {
    suspend operator fun invoke(): Flow<List<Note>> {
        return notesDatabaseRepository.getAllNotes()
    }
}