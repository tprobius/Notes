package com.tprobius.notes.presentation.noteslistfragment

import com.tprobius.notes.domain.model.Note

sealed interface NotesListState {
    data object Initial : NotesListState
    data object Loading : NotesListState
    data class Success(val notesList: List<Note>) : NotesListState
    data object Error : NotesListState
}