package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.dao.NotesDao
import com.example.notes.dao.UsersDao
import com.example.notes.model.Notes
import com.example.notes.model.Users

@Database(entities = [Notes::class, Users::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() { //function body/definition not required
    abstract fun myNotesDao(): NotesDao
    abstract fun myUsersDao(): UsersDao

    companion object //like static keyword in java?
    {
        @Volatile //annotate it to volatile for easy access
        var INSTANCE: NotesDatabase? = null //capital coz it is final instance, check and add data

        fun getDatabaseInstance(context: Context): NotesDatabase {
            var tempInstance: NotesDatabase? =
                INSTANCE //INSTANCE is type nullable NotesDatabase (can make is shorter when type inference)
            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val roomDatabaseInstance =
                        Room.databaseBuilder(context, NotesDatabase::class.java, "Notes").allowMainThreadQueries().build()
                    INSTANCE = roomDatabaseInstance
                    return roomDatabaseInstance
                    //allowMainThreadQueries -> we can do any work on main thread
                }
            }
        }
    }



}