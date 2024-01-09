package com.tprobius.notes.navigation

import com.github.terrakok.cicerone.Router
import com.tprobius.notes.presentation.settingsfragment.SettingsRouter

class SettingsRouterImpl(
    private val router: Router
) : SettingsRouter {
    override fun closeSettings() {
        router.exit()
    }
}