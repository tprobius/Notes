package com.tprobius.notes.domain.usecases

import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.repository.NotesDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteNotesUseCase(
    private val notesDatabaseRepository: NotesDatabaseRepository
) {
    suspend operator fun invoke(): Flow<List<Note>> {
        return notesDatabaseRepository.getFavoriteNotes()
    }
}