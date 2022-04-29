package com.example.workingdatabase.database

import android.app.Application
import android.content.Context
import androidx.room.Room

class WorkingDatabase : Application() {

    private var _appdatabase: AppDatabase? = null
    val appDatabase get() = requireNotNull(_appdatabase)

    override fun onCreate() {
        super.onCreate()

        _appdatabase = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "app-database"
        )
            .allowMainThreadQueries()
            .build()
    }
}

val Context.appDatabase: AppDatabase
    get() = when {
        this is WorkingDatabase -> appDatabase
        else -> applicationContext.appDatabase
    }