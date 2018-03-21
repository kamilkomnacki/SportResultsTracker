package com.komnacki.sportresultstracker

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.komnacki.sportresultstracker.database.User
import com.komnacki.sportresultstracker.database.UserRepository


class SportsListViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository = UserRepository()
    private var listOfUsers = userRepository.getAll()

    fun insert(user: User){
        userRepository.insert(user)
    }

    fun getUserList(): LiveData<List<User>>{
        return listOfUsers
    }
}