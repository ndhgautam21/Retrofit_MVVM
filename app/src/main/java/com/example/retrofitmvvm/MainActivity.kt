package com.example.retrofitmvvm

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.example.retrofitmvvm.activity.CreateUserActivity
import com.example.retrofitmvvm.adapter.UserAdapter
import com.example.retrofitmvvm.api.RequestHelper
import com.example.retrofitmvvm.api.UsersRequest
import com.example.retrofitmvvm.databinding.ActivityMainBinding
import com.example.retrofitmvvm.listener.UserListener
import com.example.retrofitmvvm.models.Users
import com.example.retrofitmvvm.repository.UsersRepository
import com.example.retrofitmvvm.view_model.MainViewModel
import com.example.retrofitmvvm.view_model.factory.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), UserListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val usersRequest = RequestHelper.getInstance().create(UsersRequest::class.java)
        viewModel = ViewModelProvider(this, MainViewModelFactory(UsersRepository(usersRequest)))[MainViewModel::class.java]

        // Binding...
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setSupportActionBar(binding.toolbar)

        binding.lifecycleOwner = this
        binding.mainViewModel = viewModel

        viewModel.getUserData()
        // user list observer
        viewModel.usersLiveData.observe(this) {
            binding.recyclerView.adapter = UserAdapter(viewModel.usersLiveData.value!!, this)
        }

        viewModel.errorLD.observe(this) {
            Toast.makeText(applicationContext, "ERROR", Toast.LENGTH_SHORT).show()
        }

        binding.fabButton.setOnClickListener {
            startActivity(Intent(applicationContext, CreateUserActivity::class.java))
        }
        //binding.recyclerView.adapter = UserAdapter(viewModel.usersLiveData.value!!)
        //binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)

//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAnchorView(R.id.fab)
//                .setAction("Action", null).show()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun addUser(users: Users, view : View) {
        Snackbar.make(view, users.name , Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
        //Toast.makeText(applicationContext, users.login, Toast.LENGTH_SHORT).show()
    }
}