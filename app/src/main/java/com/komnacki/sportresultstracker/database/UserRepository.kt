package com.komnacki.sportresultstracker.database

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
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
        insertAsyncTask().execute(user)
    }

    fun update(user: User){
        updateAsyncTask().execute(user)
    }

    fun delete(user: User){
        deleteAsyncTask().execute(user)
    }

    fun getAll(): LiveData<List<User>> {
        return list
    }

    @SuppressLint("StaticFieldLeak")
    private inner class insertAsyncTask() : AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg p0: User): Void? {
            userDAO.insert(p0.get(0))
            return null
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class deleteAsyncTask() : AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg p0: User): Void? {
            userDAO.delete(p0[0])
            return null
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class updateAsyncTask() : AsyncTask<User, Void, Void>() {
        override fun doInBackground(vararg p0: User): Void? {
            userDAO.update(p0[0])
            return null
        }
    }
}
