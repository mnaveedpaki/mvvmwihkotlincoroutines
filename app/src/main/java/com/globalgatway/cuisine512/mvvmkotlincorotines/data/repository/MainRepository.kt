package com.globalgatway.cuisine512.mvvmkotlincorotines.data.repository

import com.globalgatway.cuisine512.mvvmkotlincorotines.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}