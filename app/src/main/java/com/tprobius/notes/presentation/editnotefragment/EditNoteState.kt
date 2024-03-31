package com.tprobius.notes.presentation.editnotefragment

import com.tprobius.notes.domain.model.Note

sealed interface EditNoteState {

    data object Initial : EditNoteState
    data object Loading : EditNoteState
    data class Success(val note: Note) : EditNoteState
    data object Error : EditNoteState
}