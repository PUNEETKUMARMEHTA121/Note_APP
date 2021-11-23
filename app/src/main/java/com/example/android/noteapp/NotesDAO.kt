package com.example.android.noteapp

import androidx.lifecycle.LiveData
import androidx.room.*

// annotation for dao class.
@Dao
interface NotesDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert( note :Note)


    @Delete
     fun delete(note: Note)


    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>


    @Update
    fun update(note: Note)

}