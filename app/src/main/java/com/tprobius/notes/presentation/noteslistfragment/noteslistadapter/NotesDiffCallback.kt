package com.tprobius.notes.presentation.noteslistfragment.noteslistadapter

import androidx.recyclerview.widget.DiffUtil
import com.tprobius.notes.domain.model.Note

class NotesDiffCallback : DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Note, newItem: Note) =
        oldItem.id == newItem.id
}