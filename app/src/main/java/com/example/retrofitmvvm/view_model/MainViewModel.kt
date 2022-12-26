package com.example.retrofitmvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitmvvm.models.Users
import com.example.retrofitmvvm.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    val usersLiveData: LiveData<List<Users>>
        get() = usersRepository.usersLiveData

    val loadingLiveData: LiveData<String>
        get() = usersRepository.loadingLD

    val errorLD: LiveData<Boolean>
        get() = usersRepository.errorLD

    fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.getUsers()
        }
    }
}