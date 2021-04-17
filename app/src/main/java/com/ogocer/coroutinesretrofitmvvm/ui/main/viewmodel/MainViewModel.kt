package com.ogocer.coroutinesretrofitmvvm.ui.main.viewmodel

import androidx.lifecycle.liveData
import com.ogocer.coroutinesretrofitmvvm.data.repository.MainRepository
import com.ogocer.coroutinesretrofitmvvm.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository) {

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        }catch (exception : Exception){
            emit(Resource.error(data = null,message = exception.message ?: "Error"))
        }

    }

}