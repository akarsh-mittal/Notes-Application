package com.example.notes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.model.Notes

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes") //custom query
    fun getNotes() : LiveData<List<Notes>>

    @Insert//try add (onConflict=OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Delete
    fun deleteNotes(notes: Notes)

    @Delete
    fun deleteAllNotes(allNotes: List<Notes>)

    @Update
    fun updateNotes(notes: Notes)

}