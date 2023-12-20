package com.tprobius.notes.presentation.noteslistfragment.noteslistadapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.tprobius.notes.R
import com.tprobius.notes.databinding.ItemListBinding
import com.tprobius.notes.domain.model.Note
import java.text.SimpleDateFormat
import java.util.Date

class NotesViewHolder(
    private var binding: ItemListBinding,
    private val onClickListener: (Note) -> Unit,
    private val onFavoriteListener: (Note) -> Unit,
    private val onDeleteListener: (Note) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SimpleDateFormat")
    fun bind(note: Note) {
        val date = Date(note.timestamp)
        val format = SimpleDateFormat("dd.mm.yyyy HH:mm")

        binding.titleTextView.text = note.title
        binding.contentTextView.text = note.content
        binding.dateTextView.text = format.format(date).toString()

        setFavoriteIcon(note)

        itemView.setOnClickListener {
            onClickListener(note)
        }

        binding.favoriteImageView.setOnClickListener {
            onFavoriteListener(note)
            note.isFavorite = !note.isFavorite
            setFavoriteIcon(note)
        }

        binding.deleteImageView.setOnClickListener {
            onDeleteListener(note)
        }
    }

    private fun setFavoriteIcon(note: Note) {
        if (note.isFavorite) {
            binding.favoriteImageView.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            binding.favoriteImageView.setImageResource(R.drawable.ic_favorite_border)
        }
    }
}