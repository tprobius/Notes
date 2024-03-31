package com.tprobius.notes.domain.usecases

import com.tprobius.notes.domain.repository.NotesDatabaseRepository

class SetNoteFavoriteUseCase(
    private val notesDatabaseRepository: NotesDatabaseRepository
) {

    suspend operator fun invoke(id: Long, isFavorite: Boolean) {
        return notesDatabaseRepository.setNoteFavorite(id, isFavorite)
    }
}