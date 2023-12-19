package com.tprobius.notes.presentation.noteslistfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.usecases.AddNewNoteUseCase
import com.tprobius.notes.domain.usecases.DeleteNoteUseCase
import com.tprobius.notes.domain.usecases.GetAllNotesUseCase
import com.tprobius.notes.presentation.noteslistfragment.NotesListState.Initial
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesListViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val addNoteUseCase: AddNewNoteUseCase,
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

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            _state.value = NotesListState.Loading
            try {
                deleteNoteUseCase(note)
                getAllNotes()
            } catch (e: Exception) {
                _state.value = NotesListState.Error
            }
        }
    }

    fun restoreNote(note: Note) {
        viewModelScope.launch {
            _state.value = NotesListState.Loading
            try {
                addNoteUseCase(note)
                getAllNotes()
            } catch (_: Exception) {
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