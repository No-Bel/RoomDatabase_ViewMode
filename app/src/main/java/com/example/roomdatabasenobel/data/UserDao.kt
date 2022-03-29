package com.example.roomdatabasenobel.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserDao(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllDataDao(): LiveData<List<User>>

    @Update
    suspend fun updateUserDao(user: User)
}