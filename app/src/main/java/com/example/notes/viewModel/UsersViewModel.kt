package com.example.notes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notes.database.NotesDatabase
import com.example.notes.model.Notes
import com.example.notes.model.Users
import com.example.notes.repository.NotesRepository
import com.example.notes.repository.UsersRepository

class UsersViewModel(application: Application) : AndroidViewModel(application) {//AndroidViewModel works through whole application

    val repository : UsersRepository

    init { //will be the first to run, making instance of repository

        val dao= NotesDatabase.getDatabaseInstance(application).myUsersDao()
        repository= UsersRepository(dao)

    }

    fun getUsers() : LiveData<List<Users>> {
        return repository.getAllUsers()
    }

    fun insertUsers(users: Users) {
        repository.insertUsers(users)
    }

}