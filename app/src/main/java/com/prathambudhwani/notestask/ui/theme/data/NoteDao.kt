package com.prathambudhwani.notestask.ui.theme.data
import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow
@Dao
interface NoteDao {
    @Query("SELECT*FROM Note")
    fun getAllNotes():Flow<List<Note>>

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)
}