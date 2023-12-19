package com.tprobius.notes.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tprobius.notes.domain.model.Note

@Entity(tableName = "notes")
data class NoteDbEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    val timestamp: Long
) {
    fun mapToNote() = Note(id, title, content, timestamp)

    companion object {
        fun mapToNoteDbEntity(note: Note) =
            NoteDbEntity(note.id, note.title, note.content, note.timestamp)
    }
}