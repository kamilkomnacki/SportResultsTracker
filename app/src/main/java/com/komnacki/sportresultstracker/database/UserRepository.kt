package com.komnacki.sportresultstracker.database

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.komnacki.sportresultstracker.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository(var application: Application) {
    @Inject
    lateinit var userDAO: UserDAO
    var list: LiveData<List<User>>

    init {
        App.app().appComponent()?.inject(this)
        list = userDAO.getAll()
    }

    fun insert(user: User) {
        insertAsyncTask(userDAO).execute(user)
    }

    fun getAll(): LiveData<List<User>> {
        return list
    }

    @SuppressLint("StaticFieldLeak")
    private inner class insertAsyncTask(userDAO: UserDAO) : AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg p0: User): Void? {
            userDAO.insert(p0.get(0))
            return null
        }
    }
}