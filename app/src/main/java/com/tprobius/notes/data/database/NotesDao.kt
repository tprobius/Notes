package com.tprobius.notes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tprobius.notes.data.entities.NoteDbEntity

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<NoteDbEntity>

    @Query("SELECT * FROM notes WHERE isFavorite")
    suspend fun getFavoriteNotes(): List<NoteDbEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Long): NoteDbEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewNote(note: NoteDbEntity)

    @Query("UPDATE notes SET isFavorite = :isFavorite WHERE id = :id")
    fun setNoteFavorite(id: Long, isFavorite: Boolean)

    @Delete
    suspend fun deleteNote(note: NoteDbEntity)
}