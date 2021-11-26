package com.example.android.noteapp

import androidx.lifecycle.LiveData
import androidx.room.*

// annotation for dao class.
@Dao
abstract class NotesDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
       abstract fun  insert( note :Note)


    @Delete
     abstract fun delete(note: Note)


    @Query("Select * from notesTable order by id ASC")
     abstract fun getAllNotes(): LiveData<List<Note>>


    @Update
     abstract fun update(note: Note)

}