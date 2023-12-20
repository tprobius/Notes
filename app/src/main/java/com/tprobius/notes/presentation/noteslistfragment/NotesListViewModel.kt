package com.tprobius.notes.presentation.noteslistfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.usecases.AddNewNoteUseCase
import com.tprobius.notes.domain.usecases.DeleteNoteUseCase
import com.tprobius.notes.domain.usecases.GetAllNotesUseCase
import com.tprobius.notes.domain.usecases.GetFavoriteNotesUseCase
import com.tprobius.notes.presentation.noteslistfragment.NotesListState.Initial
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesListViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val getFavoriteNotesUseCase: GetFavoriteNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val addNoteUseCase: AddNewNoteUseCase,
    private val router: NotesListRouter
) : ViewModel() {
    private var _state: MutableStateFlow<NotesListState> = MutableStateFlow(Initial)
    val state: StateFlow<NotesListState> = _state

    suspend fun getAllNotes() {
        viewModelScope.launch {
            _state.value = NotesListState.Loading
            try {
                getAllNotesUseCase().collect {
                    _state.value = NotesListState.Success(it)
                }
            } catch (e: Exception) {
                _state.value = NotesListState.Error
            }
        }.join()
    }

    suspend fun getFavoriteNotes() {
        viewModelScope.launch {
            _state.value = NotesListState.Loading
            try {
                getFavoriteNotesUseCase().collect {
                    _state.value = NotesListState.Success(it)
                }
            } catch (e: Exception) {
                _state.value = NotesListState.Error
            }
        }.join()
    }

    suspend fun updateNote(note: Note) {
        viewModelScope.launch {
            _state.value = NotesListState.Loading
            try {
                addNoteUseCase(note)
            } catch (_: Exception) {
                _state.value = NotesListState.Error
            }
        }.join()
    }

    suspend fun deleteNote(note: Note) {
        viewModelScope.launch {
            _state.value = NotesListState.Loading
            try {
                deleteNoteUseCase(note)
            } catch (e: Exception) {
                _state.value = NotesListState.Error
            }
        }.join()
    }

    suspend fun restoreNote(note: Note) {
        viewModelScope.launch {
            _state.value = NotesListState.Loading
            try {
                addNoteUseCase(note)
            } catch (_: Exception) {
                _state.value = NotesListState.Error
            }
        }.join()
    }

    fun addNewNote() {
        router.openAddNote()
    }

    fun editNote(note: Note) {
        router.openEditNote(note)
    }
}