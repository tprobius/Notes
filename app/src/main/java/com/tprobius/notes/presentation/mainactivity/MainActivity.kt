package com.tprobius.notes.presentation.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.tprobius.notes.R
import com.tprobius.notes.presentation.noteslistfragment.getListScreen
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val router: Router by inject()
    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator = AppNavigator(this, R.id.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router.newRootScreen(getListScreen())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}