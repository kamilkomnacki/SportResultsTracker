package com.komnacki.sportresultstracker.usersActivity

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.komnacki.sportresultstracker.database.User
import com.komnacki.sportresultstracker.database.UserRepository


class UsersListViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository = UserRepository()
    private var listOfUsers = userRepository.getAll()

    fun insert(user: User){
        userRepository.insert(user)
    }

    fun getUserList(): LiveData<List<User>>{
        return listOfUsers
    }

    fun update(user: User) {
        userRepository.update(user)
    }

    fun getUser(id: Long?): User {
        for(user in listOfUsers.value.orEmpty()){
            if(user.id == id)
                return user
        }
        return User()
    }

    fun isNameExist(name: String): Boolean{
        for(sport in listOfUsers.value.orEmpty()){
            if(sport.name == name)
                return true
        }
        return false
    }
}