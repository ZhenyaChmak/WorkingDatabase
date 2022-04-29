package com.example.workingdatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.workingdatabase.database.queryDatabase.UserDao
import com.example.workingdatabase.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}