package com.tprobius.notes

import android.app.Application
import com.tprobius.notes.di.databaseModule
import com.tprobius.notes.di.navigationModule
import com.tprobius.notes.di.useCasesModule
import com.tprobius.notes.di.viewModelModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NotesApp)
            modules(
                databaseModule,
                viewModelModule,
                useCasesModule,
                navigationModule
            )
        }
    }
}