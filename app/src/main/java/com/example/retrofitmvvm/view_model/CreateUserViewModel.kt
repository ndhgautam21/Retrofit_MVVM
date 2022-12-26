package com.example.retrofitmvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitmvvm.models.Users
import com.example.retrofitmvvm.repository.UsersRepository
import kotlinx.coroutines.launch

class CreateUserViewModel(private val usersRepository: UsersRepository) : ViewModel() {

    val createLD: LiveData<Users>
        get() = usersRepository.createUserLD

    val getUserByIdLD: LiveData<Users>
        get() = usersRepository.getUserByIdLD

    val loadingLD: LiveData<String>
        get() = usersRepository.loadingLD

    val errorLD: LiveData<Boolean>
        get() = usersRepository.errorLD

    val isCreate : MutableLiveData<String?> = MutableLiveData("ADD User")

    val name = MutableLiveData<String>()
    val emailId = MutableLiveData<String>()
    val phoneNo = MutableLiveData<String>()
    val image = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            usersRepository.getUserById(9)
        }

    }

    fun getUserById(userById : Users?) {
        userById?.let { user ->
            name.postValue(user.name)
            emailId.postValue(user.email_id)
            phoneNo.postValue(user.phone_no)
            image.postValue(user.image)
            isCreate.postValue("Update User")
        }
    }

    fun addUser() {
        val user = Users(
            id = null,
            name = name.value!!,
            email_id = emailId.value!!,
            phone_no = phoneNo.value!!,
            image = image.value!!,
            create_at = null,
            updated_at = null
        )
        viewModelScope.launch {
            if (isCreate.value.equals("ADD User"))
                usersRepository.createUser(user)
            else usersRepository.updateUser(user, 9)
        }
    }
}