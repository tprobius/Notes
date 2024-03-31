package com.tprobius.notes.navigation

import com.github.terrakok.cicerone.Router
import com.tprobius.notes.presentation.addnotefragment.AddNoteRouter
import com.tprobius.notes.presentation.noteslistfragment.getListScreen

class AddNoteRouterImpl(
    private val router: Router
) : AddNoteRouter {

    override fun openNotesListScreen() {
        router.navigateTo(getListScreen())
    }
}