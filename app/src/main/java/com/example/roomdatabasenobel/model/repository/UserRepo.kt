package com.example.roomdatabasenobel.model.repository

import androidx.lifecycle.LiveData
import com.example.roomdatabasenobel.model.data.User
import com.example.roomdatabasenobel.model.data.UserDao

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