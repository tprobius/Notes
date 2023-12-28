package com.tprobius.notes.domain.usecases

import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.repository.NotesDatabaseRepository

class GetAllNotesUseCase(
    private val notesDatabaseRepository: NotesDatabaseRepository
) {
    suspend operator fun invoke(): List<Note> {
        return notesDatabaseRepository.getAllNotes()
    }
}