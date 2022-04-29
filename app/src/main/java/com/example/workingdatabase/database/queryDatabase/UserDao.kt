package com.example.workingdatabase.database.queryDatabase

import androidx.room.*
import com.example.workingdatabase.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)


}