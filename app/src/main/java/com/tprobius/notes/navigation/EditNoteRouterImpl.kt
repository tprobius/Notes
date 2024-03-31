package com.tprobius.notes.navigation

import com.github.terrakok.cicerone.Router
import com.tprobius.notes.presentation.editnotefragment.EditNoteRouter
import com.tprobius.notes.presentation.noteslistfragment.getListScreen

class EditNoteRouterImpl(
    private val router: Router
) : EditNoteRouter {

    override fun openNotesListScreen() {
        router.navigateTo(getListScreen())
    }
}