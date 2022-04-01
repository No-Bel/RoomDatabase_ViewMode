package com.example.roomdatabasenobel.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabasenobel.data.User
import com.example.roomdatabasenobel.data.UserDao

class UserRepo(private val userDao: UserDao) {


    val readAllDataRp: LiveData<List<User>> = userDao.readAllDataDao()

    suspend fun addUserRp(user: User) {
        userDao.addUserDao(user)
    }

    suspend fun updateUserRp(user: User) {
        userDao.updateUserDao(user)
    }

    suspend fun deleteUserRp(user: User) {
        userDao.deleteUserDao(user)
    }

    suspend fun deleteAllUserRp() {
        userDao.deleteAllUserDao()
    }
}