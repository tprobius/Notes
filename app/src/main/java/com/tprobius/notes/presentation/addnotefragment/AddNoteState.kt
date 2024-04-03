package com.tprobius.notes.presentation.addnotefragment

sealed interface AddNoteState {

    data object Initial : AddNoteState
    data object Loading : AddNoteState
    data object Success : AddNoteState
    data object Error : AddNoteState
}