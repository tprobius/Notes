package com.tprobius.notes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tprobius.notes.domain.model.Note

@Entity(tableName = "notes")
data class NoteDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String
) {
    fun mapToNote() = Note(title, content)

    companion object {
        fun mapToNoteDbEntity(note: Note) = NoteDbEntity(0, note.title, note.content)
    }
}