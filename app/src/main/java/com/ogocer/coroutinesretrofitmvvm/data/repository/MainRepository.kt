package com.ogocer.coroutinesretrofitmvvm.data.repository

import com.ogocer.coroutinesretrofitmvvm.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}