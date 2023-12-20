package com.tprobius.notes.presentation.noteslistfragment.noteslistadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.tprobius.notes.databinding.ItemListBinding
import com.tprobius.notes.domain.model.Note

class NotesListAdapter(
    private val onClickListener: (Note) -> Unit,
    private val onFavoriteListener: (Note) -> Unit,
    private val onDeleteListener: (Note) -> Unit
) : ListAdapter<Note, NotesViewHolder>(NotesDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder =
        NotesViewHolder(
            ItemListBinding.inflate(LayoutInflater.from(parent.context)),
            onClickListener,
            onFavoriteListener,
            onDeleteListener
        )

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) =
        holder.bind(getItem(position))
}