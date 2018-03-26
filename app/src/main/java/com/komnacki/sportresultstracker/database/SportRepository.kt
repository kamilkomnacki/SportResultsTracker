package com.komnacki.sportresultstracker.database

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
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
        insertAsyncTask().execute(sport)
    }

    fun delete(sport: Sport) {
        deleteAsyncTask().execute(sport)
    }

    fun getAll(userId: Long): LiveData<List<Sport>> {
        Log.d(LOG_TAG, "User ID to get sports: " + userId)
        return sportDAO.getAll(userId)
    }

    @SuppressLint("StaticFieldLeak")
    private inner class insertAsyncTask() : AsyncTask<Sport, Void, Void>() {
        override fun doInBackground(vararg p0: Sport): Void? {
            sportDAO.insert(p0.get(0))
            return null
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class deleteAsyncTask() : AsyncTask<Sport, Void, Void>() {
        override fun doInBackground(vararg p0: Sport): Void? {
            sportDAO.delete(p0[0])
            return null
        }
    }
}
