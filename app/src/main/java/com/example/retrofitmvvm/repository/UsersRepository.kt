package com.example.retrofitmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitmvvm.api.UsersRequest
import com.example.retrofitmvvm.models.Users
import java.util.Objects

class UsersRepository(private val usersRequest: UsersRequest) {

    private val usersMLD = MutableLiveData<List<Users>>().apply { value = listOf() }
    private val getUserByIdMLD = MutableLiveData<Users>()
    private val createUserMLD = MutableLiveData<Users>()
    private val updateUserMLD = MutableLiveData<Users>()
    private val deleteUserMLD = MutableLiveData<Objects>()

    private val loadingMLD = MutableLiveData<String>().apply { value = "No" }
    private val errorMLD = MutableLiveData<Boolean>()

    val usersLiveData: LiveData<List<Users>>
        get() = usersMLD

    val getUserByIdLD: LiveData<Users>
        get() = getUserByIdMLD

    val createUserLD: LiveData<Users>
        get() = createUserMLD

    val updateUserLD: LiveData<Users>
        get() = updateUserMLD

    val deleteUserLD: LiveData<Objects>
        get() = deleteUserMLD

    val loadingLD: LiveData<String>
        get() = loadingMLD

    val errorLD: LiveData<Boolean>
        get() = errorMLD

    /**
     *
     */
    suspend fun getUsers() {
        loadingMLD.postValue("Yes")
        val result = usersRequest.getUsers()
        if (result.isSuccessful) {
            usersMLD.postValue(result.body())
        } else {
            errorMLD.postValue(true)
        }
        loadingMLD.postValue("No")
    }

    suspend fun getUserById(id : Int) {
        loadingMLD.postValue("Yes")
        val result = usersRequest.getUserById(id)
        if (result.isSuccessful) {
            getUserByIdMLD.postValue(result.body())
        } else {
            errorMLD.postValue(true)
        }
        loadingMLD.postValue("No")
    }

    /**
     *
     */
    suspend fun createUser(users: Users) {
        loadingMLD.postValue("Yes")
        val result = usersRequest.createUser(users)
        if (result.isSuccessful) {
            createUserMLD.postValue(result.body())
        } else {
            errorMLD.postValue(true)
        }
        loadingMLD.postValue("No")
    }

    /**
     *
     */
    suspend fun updateUser(users: Users, id : Int) {
        loadingMLD.postValue("Yes")
        val result = usersRequest.updateUser(users, id)
        if (result.isSuccessful) {
            updateUserMLD.postValue(result.body())
        } else {
            errorMLD.postValue(true)
        }
        loadingMLD.postValue("No")
    }

    /**
     *
     */
    suspend fun deleteUser() {
        loadingMLD.postValue("Yes")
        val result = usersRequest.deleteUser(23)
        if (result.isSuccessful) {
            deleteUserMLD.postValue(result.body())
        } else {
            errorMLD.postValue(true)
        }
        loadingMLD.postValue("No")
    }


}