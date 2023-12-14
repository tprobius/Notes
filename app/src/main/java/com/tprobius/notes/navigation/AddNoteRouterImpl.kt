package com.tprobius.notes.navigation

import com.github.terrakok.cicerone.Router
import com.tprobius.notes.presentation.addnotefragment.AddNoteRouter
import com.tprobius.notes.presentation.listfragment.getListScreen

class AddNoteRouterImpl(
    private val router: Router
) : AddNoteRouter {
    override fun openNotesList() {
        router.navigateTo(getListScreen())
    }
}