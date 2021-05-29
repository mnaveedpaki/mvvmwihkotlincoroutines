package com.globalgatway.cuisine512.mvvmkotlincorotines.data.api

import com.globalgatway.cuisine512.mvvmkotlincorotines.data.model.User
import retrofit2.http.GET


interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users")
    suspend fun getUsers(
        name: String
    ): List<User>

}