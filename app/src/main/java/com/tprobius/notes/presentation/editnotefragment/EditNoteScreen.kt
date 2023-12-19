package com.tprobius.notes.presentation.editnotefragment

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.tprobius.notes.domain.model.Note

fun getEditNoteScreen(note: Note) = FragmentScreen { EditNoteFragment.newInstance(note) }