package com.tprobius.notes.di

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.tprobius.notes.data.database.NotesDatabase
import com.tprobius.notes.data.repository.NotesDatabaseRepositoryImpl
import com.tprobius.notes.domain.repository.NotesDatabaseRepository
import com.tprobius.notes.domain.usecases.AddNewNoteUseCase
import com.tprobius.notes.domain.usecases.DeleteNoteUseCase
import com.tprobius.notes.domain.usecases.GetAllNotesUseCase
import com.tprobius.notes.domain.usecases.GetFavoriteNotesUseCase
import com.tprobius.notes.domain.usecases.GetNoteByIdUseCase
import com.tprobius.notes.domain.usecases.SetNoteFavoriteUseCase
import com.tprobius.notes.navigation.AddNoteRouterImpl
import com.tprobius.notes.navigation.EditNoteRouterImpl
import com.tprobius.notes.navigation.NotesListRouterImpl
import com.tprobius.notes.presentation.addnotefragment.AddNoteRouter
import com.tprobius.notes.presentation.addnotefragment.AddNoteViewModel
import com.tprobius.notes.presentation.editnotefragment.EditNoteRouter
import com.tprobius.notes.presentation.editnotefragment.EditNoteViewModel
import com.tprobius.notes.presentation.noteslistfragment.NotesListRouter
import com.tprobius.notes.presentation.noteslistfragment.NotesListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { provideNotesDatabase(notesApp = get()).notesDao }
    single<NotesDatabaseRepository> {
        NotesDatabaseRepositoryImpl(
            notesDao = get(),
            dispatcher = Dispatchers.IO
        )
    }
}

val viewModelModule = module {
    viewModel { NotesListViewModel(get(), get(), get(), get(), get(), get()) }
    viewModel { AddNoteViewModel(get(), get()) }
    viewModel { EditNoteViewModel(get(), get(), get()) }
}

val useCasesModule = module {
    single { GetAllNotesUseCase(notesDatabaseRepository = get()) }
    single { GetFavoriteNotesUseCase(notesDatabaseRepository = get()) }
    single { AddNewNoteUseCase(notesDatabaseRepository = get()) }
    single { GetNoteByIdUseCase(notesDatabaseRepository = get()) }
    single { SetNoteFavoriteUseCase(notesDatabaseRepository = get()) }
    single { DeleteNoteUseCase(notesDatabaseRepository = get()) }
}

val navigationModule = module {
    single { Cicerone.create() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }

    factory<NotesListRouter> { NotesListRouterImpl(get()) }
    factory<AddNoteRouter> { AddNoteRouterImpl(get()) }
    factory<EditNoteRouter> { EditNoteRouterImpl(get()) }
}

fun provideNotesDatabase(notesApp: Application): NotesDatabase {
    return Room.databaseBuilder(
        notesApp,
        NotesDatabase::class.java,
        NotesDatabase.DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()
}