package com.tprobius.notes.presentation.editnotefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.usecases.AddNewNoteUseCase
import com.tprobius.notes.domain.usecases.GetNoteByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditNoteViewModel(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val addNewNoteUseCase: AddNewNoteUseCase,
    private val router: EditNoteRouter
) : ViewModel() {
    private var _state: MutableStateFlow<EditNoteState> = MutableStateFlow(EditNoteState.Initial)
    val state: StateFlow<EditNoteState> = _state

    fun getNoteById(note: Note) {
        viewModelScope.launch {
            _state.value = EditNoteState.Loading
            try {
                getNoteByIdUseCase(note.id)
                _state.value = EditNoteState.Success(note)
            } catch (_: Exception) {
                _state.value = EditNoteState.Error
            }
        }
    }

    fun saveNote(note: Note) {
        viewModelScope.launch {
            _state.value = EditNoteState.Loading
            try {
                addNewNoteUseCase(note)
                _state.value = EditNoteState.Success(note)
                router.openNotesList()
            } catch (_: Exception) {
                _state.value = EditNoteState.Error
            }
        }
    }
}