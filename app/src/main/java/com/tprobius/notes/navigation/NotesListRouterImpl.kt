package com.tprobius.notes.navigation

import com.github.terrakok.cicerone.Router
import com.tprobius.notes.domain.model.Note
import com.tprobius.notes.presentation.addnotefragment.getAddNoteScreen
import com.tprobius.notes.presentation.editnotefragment.getEditNoteScreen
import com.tprobius.notes.presentation.noteslistfragment.NotesListRouter

class NotesListRouterImpl(
    private val router: Router
) : NotesListRouter {

    override fun openAddNoteScreen() {
        router.navigateTo(getAddNoteScreen())
    }

    override fun openEditNoteScreen(note: Note) {
        router.navigateTo(getEditNoteScreen(note))
    }

}