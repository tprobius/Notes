package com.tprobius.notes.presentation.addnotefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.usecases.AddNewNoteUseCase
import kotlinx.coroutines.launch

class AddNoteViewModel(
    private val addNewNoteUseCase: AddNewNoteUseCase,
    private val router: AddNoteRouter
) : ViewModel() {
    private var _state: MutableLiveData<AddNoteState> = MutableLiveData()
    val state: LiveData<AddNoteState> = _state

    fun addNewNote(note: Note) {
        viewModelScope.launch {
            _state.postValue(AddNoteState.Loading)
            try {
                addNewNoteUseCase(note)
                _state.postValue(AddNoteState.Success(note))
                router.openNotesList()
            } catch (_: Exception) {
                _state.postValue(AddNoteState.Error)
            }
        }
    }
}