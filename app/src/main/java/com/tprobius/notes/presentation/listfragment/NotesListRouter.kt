package com.tprobius.notes.presentation.listfragment

import com.tprobius.notes.domain.model.Note

interface NotesListRouter {
    fun openAddNote()

    fun openEditNote(note: Note)
}