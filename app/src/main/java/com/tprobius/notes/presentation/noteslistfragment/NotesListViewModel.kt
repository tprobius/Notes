package com.tprobius.notes.presentation.noteslistfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.domain.usecases.AddNewNoteUseCase
import com.tprobius.notes.domain.usecases.DeleteNoteUseCase
import com.tprobius.notes.domain.usecases.GetAllNotesUseCase
import com.tprobius.notes.domain.usecases.GetFavoriteNotesUseCase
import com.tprobius.notes.domain.usecases.SetNoteFavoriteUseCase
import kotlinx.coroutines.launch

class NotesListViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val getFavoriteNotesUseCase: GetFavoriteNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val addNoteUseCase: AddNewNoteUseCase,
    private val setNoteFavoriteUseCase: SetNoteFavoriteUseCase,
    private val router: NotesListRouter
) : ViewModel() {
    private var _state: MutableLiveData<NotesListState> = MutableLiveData()
    val state: LiveData<NotesListState> = _state

    fun getAllNotes() {
        viewModelScope.launch {
            _state.postValue(NotesListState.Loading)
            try {
                getAllNotesUseCase().let {
                    _state.postValue(NotesListState.Success(it))
                }
            } catch (e: Exception) {
                _state.postValue(NotesListState.Error)
            }
        }
    }

    fun getFavoriteNotes() {
        viewModelScope.launch {
            _state.postValue(NotesListState.Loading)
            try {
                getFavoriteNotesUseCase().let {
                    _state.postValue(NotesListState.Success(it))
                }
            } catch (e: Exception) {
                _state.postValue(NotesListState.Error)
            }
        }
    }

    suspend fun updateNote(id: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            _state.postValue(NotesListState.Loading)
            try {
                setNoteFavoriteUseCase(id, isFavorite)
            } catch (_: Exception) {
                _state.postValue(NotesListState.Error)
            }
        }.join()
    }

    suspend fun deleteNote(note: Note) {
        viewModelScope.launch {
            _state.postValue(NotesListState.Loading)
            try {
                deleteNoteUseCase(note)
            } catch (e: Exception) {
                _state.postValue(NotesListState.Error)
            }
        }.join()
    }

    suspend fun restoreNote(note: Note) {
        viewModelScope.launch {
            _state.postValue(NotesListState.Loading)
            try {
                addNoteUseCase(note)
            } catch (_: Exception) {
                _state.postValue(NotesListState.Error)
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