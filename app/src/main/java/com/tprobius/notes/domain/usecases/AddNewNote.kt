package com.tprobius.notes.domain.usecases

import com.tprobius.notes.domain.entities.Note
import com.tprobius.notes.domain.repository.NotesDatabaseRepository

class AddNewNote(
    private val notesDatabaseRepository: NotesDatabaseRepository
) {
    suspend operator fun invoke(note: Note) {
        notesDatabaseRepository.addNewNote(note)
    }
}