package com.tprobius.notes.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tprobius.notes.data.model.NoteDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NoteDbEntity>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Long): NoteDbEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewNote(note: NoteDbEntity)

    @Delete
    suspend fun deleteNote(note: NoteDbEntity)
}