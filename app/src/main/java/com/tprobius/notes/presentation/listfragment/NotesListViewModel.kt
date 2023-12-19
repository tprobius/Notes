package com.tprobius.notes.presentation.listfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.usecases.GetAllNotesUseCase
import com.tprobius.notes.presentation.listfragment.NotesListState.Initial
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesListViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val router: NotesListRouter
) : ViewModel() {
    private var _state: MutableStateFlow<NotesListState> = MutableStateFlow(Initial)
    val state: StateFlow<NotesListState> = _state

    fun getAllNotes() {
        viewModelScope.launch {
            _state.value = NotesListState.Loading
            try {
                getAllNotesUseCase().collect {
                    _state.value = NotesListState.Success(it)
                }
            } catch (e: Exception) {
                _state.value = NotesListState.Error
            }
        }
    }

    fun addNewNote() {
        router.openAddNote()
    }

    fun editNote(note: Note) {
        router.openEditNote(note)
    }
}