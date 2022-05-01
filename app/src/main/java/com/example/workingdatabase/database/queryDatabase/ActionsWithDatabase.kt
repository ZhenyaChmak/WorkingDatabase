package com.example.workingdatabase.database.queryDatabase

import android.content.Context
import com.example.workingdatabase.database.appDatabase
import com.example.workingdatabase.model.User

class ActionsWithDatabase {

    companion object {
        fun QUERY_USERS(context: Context) = context
            .appDatabase
            .userDao()
            .getUsers()

        fun INSERT_USER(context: Context, name: String) = context
            .appDatabase
            .userDao()
            .insertUser(User(name = name))

        fun DELETE_USER(context: Context, user: User) = context
            .appDatabase
            .userDao()
            .deleteUser(user = user)

        fun UPDATE_USER(context: Context, user: User) = context
            .appDatabase
            .userDao()
            .updateUser(user = user)
    }

}