package com.example.notes.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.model.Notes
import com.example.notes.model.Users

@Dao
interface UsersDao {

    @Query("SELECT * FROM Users") //custom query
    fun getUsers() : LiveData<List<Users>>

    @Insert//try add (onConflict=OnConflictStrategy.REPLACE)
    fun insertUsers(users: Users)

}