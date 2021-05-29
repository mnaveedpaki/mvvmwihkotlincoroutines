package com.globalgatway.cuisine512.mvvmkotlincorotines.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.globalgatway.cuisine512.mvvmkotlincorotines.data.api.ApiHelper
import com.globalgatway.cuisine512.mvvmkotlincorotines.data.repository.MainRepository
import com.globalgatway.cuisine512.mvvmkotlincorotines.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}