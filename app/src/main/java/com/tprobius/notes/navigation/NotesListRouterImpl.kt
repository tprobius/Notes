package com.tprobius.notes.navigation

import com.github.terrakok.cicerone.Router
import com.tprobius.notes.presentation.addnotefragment.getAddNoteScreen
import com.tprobius.notes.presentation.listfragment.NotesListRouter

class NotesListRouterImpl(
    private val router: Router
) : NotesListRouter {
    override fun openAddNote() {
        router.replaceScreen(getAddNoteScreen())
    }
}