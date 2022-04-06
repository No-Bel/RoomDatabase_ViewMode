package com.example.roomdatabasenobel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabasenobel.model.repository.UserRepo

class MainViewModelFactory(private val repo: UserRepo): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repo) as T
    }
}