package com.komnacki.sportresultstracker.database

import android.arch.lifecycle.LiveData
import com.komnacki.sportresultstracker.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository() {
    @Inject
    lateinit var userDAO: UserDAO
    var list: LiveData<List<User>>

    init {
        App.app().appComponent()?.inject(this)
        list = userDAO.getAll()
    }

    fun insert(user: User) {
        object : DAOAsyncProcessor<Unit>(null) {
            override fun doAsync(): Unit {
                userDAO.insert(user)
            }
        }.start()
    }

    fun update(user: User){
        object : DAOAsyncProcessor<Unit>(null) {
            override fun doAsync() {
                userDAO.update(user)
            }
        }.start()
    }

    fun delete(user: User){
        object : DAOAsyncProcessor<Unit>(null) {
            override fun doAsync() {
                userDAO.delete(user)
            }
        }.start()
    }

    fun getAll(): LiveData<List<User>> {
        return list
    }
}
