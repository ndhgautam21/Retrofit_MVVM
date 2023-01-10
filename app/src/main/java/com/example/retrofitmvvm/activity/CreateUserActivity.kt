package com.example.retrofitmvvm.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
        val id : Int = intent.getIntExtra("id", -1)
        val usersRequest = RequestHelper.getInstance().create(UsersRequest::class.java)
        viewModel = ViewModelProvider(this, CreateUserViewModelFactory(UsersRepository(usersRequest), id))[CreateUserViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_user)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.createUserLD.observe(this) { user ->
            Toast.makeText(applicationContext, user.toString(), Toast.LENGTH_SHORT).show()
            finish()
        }

        viewModel.updateUserLD.observe(this) { user ->
            Toast.makeText(applicationContext, user.toString(), Toast.LENGTH_SHORT).show()
            finish()
        }

        viewModel.errorLD.observe(this) { error ->
            if (error) {
                Toast.makeText(applicationContext, ERROR_MESSAGE, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.getUserByIdLD.observe(this) { user ->
            viewModel.getUserById(user)
        }
    }

    companion object {
        const val ERROR_MESSAGE = "Something went wrong"
    }
}