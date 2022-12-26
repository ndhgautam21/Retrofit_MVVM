package com.example.retrofitmvvm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitmvvm.R
import com.example.retrofitmvvm.api.RequestHelper
import com.example.retrofitmvvm.api.UsersRequest
import com.example.retrofitmvvm.databinding.ActivityCreateUserBinding
import com.example.retrofitmvvm.repository.UsersRepository
import com.example.retrofitmvvm.view_model.CreateUserViewModel
import com.example.retrofitmvvm.view_model.factory.CreateUserViewModelFactory

class CreateUserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCreateUserBinding
    private lateinit var viewModel: CreateUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val usersRequest = RequestHelper.getInstance().create(UsersRequest::class.java)
        viewModel = ViewModelProvider(this, CreateUserViewModelFactory(UsersRepository(usersRequest)))[CreateUserViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_user)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.createLD.observe(this) {
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_SHORT).show()
        }

        viewModel.errorLD.observe(this) {
            Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
        }

        viewModel.getUserByIdLD.observe(this) {
            viewModel.getUserById(it)
        }
    }
}