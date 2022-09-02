package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.dao.NotesDao
import com.example.notes.dao.UsersDao
import com.example.notes.model.Notes
import com.example.notes.model.Users

class UsersRepository(val dao: UsersDao) { //take dao in constructor coz all functions will me made with help of dao

    fun getAllUsers() : LiveData<List<Users>> {
        return dao.getUsers()
    }

    fun insertUsers(users: Users) {
        dao.insertUsers(users)
    }

}