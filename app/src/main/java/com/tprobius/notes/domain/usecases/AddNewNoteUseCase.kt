package com.tprobius.notes.domain.usecases

import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.repository.NotesDatabaseRepository

class AddNewNoteUseCase(
    private val notesDatabaseRepository: NotesDatabaseRepository
) {

    suspend operator fun invoke(note: Note) {
        notesDatabaseRepository.addNewNote(note)
    }
}