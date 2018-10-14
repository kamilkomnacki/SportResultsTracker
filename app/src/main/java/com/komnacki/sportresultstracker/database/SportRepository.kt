package com.komnacki.sportresultstracker.database

import android.arch.lifecycle.LiveData
import android.util.Log
import com.komnacki.sportresultstracker.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SportRepository {
    val LOG_TAG = SportRepository::class.java.name

    @Inject
    lateinit var sportDAO: SportDAO
    var list: LiveData<List<Sport>>

    init {
        App.app().appComponent()?.inject(this)
        list = sportDAO.getAll()
    }

    fun insert(sport: Sport) {
        object : DAOAsyncProcessor<Unit>(null) {
            override fun doAsync() {
                sportDAO.insert(sport)
            }
        }.start()
    }

    fun update(sport: Sport){
        object : DAOAsyncProcessor<Unit>(null) {
            override fun doAsync() {
                sportDAO.update(sport)
            }
        }.start()
    }

    fun delete(sport: Sport) {
        object : DAOAsyncProcessor<Unit>(null) {
            override fun doAsync() {
                sportDAO.delete(sport)
            }
        }.start()
    }


    fun getAll(userId: Long): LiveData<List<Sport>> {
        Log.d(LOG_TAG, "User ID to get sports: " + userId)
        return sportDAO.getAll(userId)
    }

    fun get(id: Long): LiveData<Sport>{
        return sportDAO.get(id)
    }
}
