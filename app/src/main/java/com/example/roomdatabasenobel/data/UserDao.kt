package com.example.roomdatabasenobel.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    /** ანოტაციას ვუკეთებთ Insert-ს (Data-ს ვსვამთ). **/
//    IGNORE - როგორც მივხვდი თუ ახალი უზერი იდენტური იქნება იმისა რაც გვაქვს, მაშინ იგნორს უკეთებს კონფლიქტს.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserDao(user: User)

    /** რომ წავიკითხოთ Data
     * Query - მოთხოვნა
     * The ASC command is used to sort the data returned in ascending order.**/
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllDataDao(): LiveData<List<User>>

    @Update
    suspend fun updateUserDao(user: User)

    @Delete
    suspend fun deleteUserDao(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUserDao()
}