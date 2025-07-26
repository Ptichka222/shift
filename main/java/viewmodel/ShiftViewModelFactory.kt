package com.example.shiftscedule.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shiftscedule.data.AppDatabase

class ShiftViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShiftViewModel::class.java)) {
            val dao = AppDatabase.getDatabase(application).memberDao()  // This is how we get DAO
            @Suppress("UNCHECKED_CAST")
            return ShiftViewModel(application, dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
