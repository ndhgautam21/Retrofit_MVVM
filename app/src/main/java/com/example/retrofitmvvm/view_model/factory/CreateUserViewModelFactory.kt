package com.example.retrofitmvvm.view_model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitmvvm.repository.UsersRepository
import com.example.retrofitmvvm.view_model.CreateUserViewModel

class CreateUserViewModelFactory(private val usersRepository: UsersRepository, private val id: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateUserViewModel(usersRepository, id) as T
    }
}