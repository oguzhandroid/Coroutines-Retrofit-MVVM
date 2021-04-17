package com.ogocer.coroutinesretrofitmvvm.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ogocer.coroutinesretrofitmvvm.data.api.ApiHelper
import com.ogocer.coroutinesretrofitmvvm.data.repository.MainRepository
import com.ogocer.coroutinesretrofitmvvm.ui.main.viewmodel.MainViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name!!!")
    }


}