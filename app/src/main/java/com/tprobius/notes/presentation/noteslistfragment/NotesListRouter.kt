package com.tprobius.notes.presentation.noteslistfragment

import com.tprobius.notes.domain.model.Note

interface NotesListRouter {

    fun openAddNoteScreen()

    fun openEditNoteScreen(note: Note)
}