package com.ogocer.coroutinesretrofitmvvm.data.api

import com.ogocer.coroutinesretrofitmvvm.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET
    suspend fun getUsers(): List<User>
}