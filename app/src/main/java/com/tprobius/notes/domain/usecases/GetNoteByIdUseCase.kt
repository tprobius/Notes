package com.tprobius.notes.domain.usecases

import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.repository.NotesDatabaseRepository

class GetNoteByIdUseCase(
    private val notesDatabaseRepository: NotesDatabaseRepository
) {
    suspend operator fun invoke(id: Long): Note {
        return notesDatabaseRepository.getNoteById(id)
    }
}