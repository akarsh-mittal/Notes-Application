package com.example.notes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notes.database.NotesDatabase
import com.example.notes.model.Notes
import com.example.notes.repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {//AndroidViewModel works through whole application

    val repository : NotesRepository

    init { //will be the first to run, making instance of repository

        val dao= NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository=NotesRepository(dao)

    }

    fun getNotes() : LiveData<List<Notes>> {
        return repository.getAllNotes()
    }

    fun insertNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun deleteNotes(notes: Notes) {
        repository.deleteNotes(notes)
    }

    fun deleteAllNotes(allNotes: List<Notes>) {
        repository.deleteAllNotes(allNotes)
    }

    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }


}