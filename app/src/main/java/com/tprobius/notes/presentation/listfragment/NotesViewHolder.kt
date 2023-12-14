package com.tprobius.notes.presentation.listfragment

import androidx.recyclerview.widget.RecyclerView
import com.tprobius.notes.databinding.ItemListBinding
import com.tprobius.notes.domain.model.Note

class NotesViewHolder(
    private var binding: ItemListBinding,
    private val onClickListener: (Note) -> Unit,
    private val onDeleteListener: (Note) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(note: Note) {
        binding.titleTextView.text = note.title
        binding.contentTextView.text = note.content

        itemView.setOnClickListener {
            onClickListener(note)
        }

        binding.deleteImageView.setOnClickListener {
            onDeleteListener(note)
        }
    }
}