package com.example.roomdatabasenobel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdatabasenobel.data.User
import com.example.roomdatabasenobel.data.UserDatabase
import com.example.roomdatabasenobel.repository.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {

    val readAllData: LiveData<List<User>>
    private val repo: UserRepo

    init {
        val userDao = UserDatabase.getDatabase(app).userDao()
        repo = UserRepo(userDao)
        readAllData = repo.readAllData
    }

    fun addUserVm(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUserRp(user)
        }
    }

    fun updateUserVm(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateUserRp(user)
        }
    }

}