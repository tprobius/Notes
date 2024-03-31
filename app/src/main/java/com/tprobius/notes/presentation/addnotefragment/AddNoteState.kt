package com.tprobius.notes.presentation.addnotefragment

import com.tprobius.notes.domain.model.Note

sealed interface AddNoteState {

    data object Initial : AddNoteState
    data object Loading : AddNoteState
    data class Success(val note: Note) : AddNoteState
    data object Error : AddNoteState
}