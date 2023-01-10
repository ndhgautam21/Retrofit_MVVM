package com.example.retrofitmvvm.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitmvvm.models.Users
import com.example.retrofitmvvm.repository.UsersRepository
import kotlinx.coroutines.launch

class CreateUserViewModel(
    private val usersRepository: UsersRepository,
    private val id: Int) : ViewModel() {

    val createUserLD: LiveData<Users>
        get() = usersRepository.createUserLD

    val updateUserLD: LiveData<Users>
        get() = usersRepository.updateUserLD

    val getUserByIdLD: LiveData<Users>
        get() = usersRepository.getUserByIdLD

    val loadingLD: LiveData<Boolean>
        get() = usersRepository.loadingLD

    val errorLD: LiveData<Boolean>
        get() = usersRepository.errorLD

    val isCreate: MutableLiveData<String?> = MutableLiveData(ADD_USER)

    val name = MutableLiveData<String>()
    val emailId = MutableLiveData<String>()
    val phoneNo = MutableLiveData<String>()
    val image = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            if (id != -1) {
                usersRepository.getUserById(id)
            }
        }
    }

    fun getUserById(userById: Users?) {
        userById?.let { user ->
            name.postValue(user.name)
            emailId.postValue(user.email_id)
            phoneNo.postValue(user.phone_no)
            image.postValue(user.image)
            isCreate.postValue(UPDATE_USER)
        }
    }

    fun addUser() {
        val user = Users(
            id = null,
            name = name.value!!,
            email_id = emailId.value!!,
            phone_no = phoneNo.value!!,
            image = image.value!!,
            created_at = null,
            updated_at = null
        )
        viewModelScope.launch {
            if (isCreate.value.equals(ADD_USER))
                usersRepository.createUser(user)
            else usersRepository.updateUser(user, id)
        }
    }

    companion object {
        const val ADD_USER = "ADD USER"
        const val UPDATE_USER = "UPDATE USER"
    }
}