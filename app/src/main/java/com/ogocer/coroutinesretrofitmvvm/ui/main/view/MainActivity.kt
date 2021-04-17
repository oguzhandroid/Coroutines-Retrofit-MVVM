package com.ogocer.coroutinesretrofitmvvm.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ogocer.coroutinesretrofitmvvm.R
import com.ogocer.coroutinesretrofitmvvm.data.api.ApiHelper
import com.ogocer.coroutinesretrofitmvvm.data.api.RetrofitBuilder
import com.ogocer.coroutinesretrofitmvvm.data.model.User
import com.ogocer.coroutinesretrofitmvvm.ui.base.ViewModelFactory
import com.ogocer.coroutinesretrofitmvvm.ui.main.adapter.MainAdapter
import com.ogocer.coroutinesretrofitmvvm.ui.main.viewmodel.MainViewModel
import com.ogocer.coroutinesretrofitmvvm.utils.Status

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewModel()
        setUpUI()
        setObservers()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setUpUI() {
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
            recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation
        )
        )
        recyclerView.adapter = adapter
    }

    private fun setObservers() {

        viewModel.getUsers().observe(this, Observer {
            it?.let {
                when(it.status){
                    Status.SUCCESS ->{
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        it.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR ->{
                        recyclerView.visibility = View.INVISIBLE
                        progressBar.visibility = View.GONE
                        Log.e("hey","${it.message}")
                        Toast.makeText(this,it.message,Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING->{
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
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