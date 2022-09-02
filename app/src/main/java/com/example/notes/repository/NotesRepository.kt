package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.dao.NotesDao
import com.example.notes.model.Notes

class NotesRepository(val dao: NotesDao) { //take dao in constructor coz all functions will me made with help of dao

    fun getAllNotes() : LiveData<List<Notes>> {
        return dao.getNotes()
    }

    fun insertNotes(notes: Notes) {
        dao.insertNotes(notes)
    }

    fun deleteNotes(notes: Notes) {
        dao.deleteNotes(notes)
    }

    fun deleteAllNotes(allNotes: List<Notes>) {
        dao.deleteAllNotes(allNotes)
    }

    fun updateNotes(notes: Notes) {
        dao.updateNotes(notes)
    }

}