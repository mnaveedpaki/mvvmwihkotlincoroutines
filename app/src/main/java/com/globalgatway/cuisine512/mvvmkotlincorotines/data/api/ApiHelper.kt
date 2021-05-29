package com.globalgatway.cuisine512.mvvmkotlincorotines.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()
    suspend fun getUsers(name : String) = apiService.getUsers(name)
}