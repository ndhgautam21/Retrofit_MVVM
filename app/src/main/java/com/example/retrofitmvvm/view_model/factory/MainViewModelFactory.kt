package com.example.retrofitmvvm.view_model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitmvvm.repository.UsersRepository
import com.example.retrofitmvvm.view_model.MainViewModel

class MainViewModelFactory(private val usersRepository: UsersRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(usersRepository) as T
    }
}