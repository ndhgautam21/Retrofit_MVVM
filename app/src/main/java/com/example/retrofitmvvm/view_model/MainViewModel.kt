package com.example.retrofitmvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitmvvm.models.Users
import com.example.retrofitmvvm.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Objects

class MainViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    val usersLiveData: LiveData<List<Users>>
        get() = usersRepository.usersLiveData

    val deleteUserLD: LiveData<Objects>
        get() = usersRepository.deleteUserLD

    val loadingLiveData: LiveData<Boolean>
        get() = usersRepository.loadingLD

    val errorLD: LiveData<Boolean>
        get() = usersRepository.errorLD

    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.getUsers()
        }
    }

    fun deleteUserData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.deleteUser(id)
        }
    }
}