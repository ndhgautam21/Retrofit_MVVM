package com.example.retrofitmvvm.listener

import android.view.View
import com.example.retrofitmvvm.models.Users

interface UserListener {

    fun addUser(users: Users, view : View)
}