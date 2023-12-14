package com.tprobius.notes.presentation.addnotefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.usecases.AddNewNoteUseCase
import com.tprobius.notes.presentation.addnotefragment.AddNoteState.Initial
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddNoteViewModel(
    private val addNewNoteUseCase: AddNewNoteUseCase,
    private val router: AddNoteRouter
) : ViewModel() {
    private var _state: MutableStateFlow<AddNoteState> = MutableStateFlow(Initial)
    val state: StateFlow<AddNoteState> = _state

    fun addNewNote(note: Note) {
        viewModelScope.launch {
            _state.value = AddNoteState.Loading
            try {
                addNewNoteUseCase(note)
                _state.value = AddNoteState.Success(note)
                router.openNotesList()
            } catch (_: Exception) {
                _state.value = AddNoteState.Error
            }
        }
    }

//    private fun saveNewNote() {
//        router.openNotesList()
//    }
}