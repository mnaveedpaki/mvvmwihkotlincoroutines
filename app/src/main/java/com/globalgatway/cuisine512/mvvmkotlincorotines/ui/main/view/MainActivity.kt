
package com.globalgatway.cuisine512.mvvmkotlincorotines.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.globalgatway.cuisine512.mvvmkotlincorotines.R.layout
import com.globalgatway.cuisine512.mvvmkotlincorotines.data.api.ApiHelper
import com.globalgatway.cuisine512.mvvmkotlincorotines.data.api.RetrofitBuilder
import com.globalgatway.cuisine512.mvvmkotlincorotines.data.model.User
import com.globalgatway.cuisine512.mvvmkotlincorotines.databinding.ActivityMainBinding
import com.globalgatway.cuisine512.mvvmkotlincorotines.ui.base.ViewModelFactory
import com.globalgatway.cuisine512.mvvmkotlincorotines.ui.main.adapter.MainAdapter
import com.globalgatway.cuisine512.mvvmkotlincorotines.ui.main.viewmodel.MainViewModel
import com.globalgatway.cuisine512.mvvmkotlincorotines.utils.Status.ERROR
import com.globalgatway.cuisine512.mvvmkotlincorotines.utils.Status.LOADING
import com.globalgatway.cuisine512.mvvmkotlincorotines.utils.Status.SUCCESS

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupUI()
        setupObservers()
    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
                DividerItemDecoration(
                        binding.recyclerView.context,
                        ( binding.recyclerView.layoutManager as LinearLayoutManager).orientation
                )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }
}