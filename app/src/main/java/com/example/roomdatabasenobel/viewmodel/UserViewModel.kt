package com.example.roomdatabasenobel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabasenobel.data.User
import com.example.roomdatabasenobel.repository.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** AndroidViewModel-ის განსხვავება ჩვეულებრივ ViewModel-თან არის ის,
 *  რომ ის შეიცავს Application-ის reference-ს (მისამართს). **/
class UserViewModel(private val repo: UserRepo) : ViewModel() {

    var readAllDataVm: LiveData<List<User>> = repo.readAllDataRp

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

    fun deleteUserVm(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteUserRp(user)
        }
    }

    fun deleteAllUserVm() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAllUserRp()
        }
    }
}