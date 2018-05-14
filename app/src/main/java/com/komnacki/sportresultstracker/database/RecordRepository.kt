package com.komnacki.sportresultstracker.database

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log
import com.komnacki.sportresultstracker.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordRepository {
    val LOG_TAG = RecordRepository::class.java.name

    @Inject
    lateinit var recordDAO: RecordDAO
    var list: LiveData<List<Record>>

    init {
        App.app().appComponent()?.inject(this)
        list = recordDAO.getAll()
    }

    fun insert(record: Record) {
        insertAsyncTask().execute(record)
    }

    fun update(record: Record){
        updateAsyncTask().execute(record)
    }

    fun delete(record: Record) {
        deleteAsyncTask().execute(record)
    }

    fun getAll(sportId: Long): LiveData<List<Record>> {
        Log.d(LOG_TAG, "sport ID to get records: " + sportId)
        return recordDAO.getAll(sportId)
    }

    fun get(id: Long): LiveData<Record> {
        return recordDAO.get(id)
    }


    @SuppressLint("StaticFieldLeak")
    private inner class insertAsyncTask() : AsyncTask<Record, Void, Void>() {
        override fun doInBackground(vararg p0: Record): Void? {
            recordDAO.insert(p0.get(0))
            return null
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class updateAsyncTask() : AsyncTask<Record, Void, Void>() {
        override fun doInBackground(vararg p0: Record): Void? {
            recordDAO.update(p0[0])
            return null
        }
    }

    @SuppressLint("StaticFieldLeak")
    private inner class deleteAsyncTask() : AsyncTask<Record, Void, Void>() {
        override fun doInBackground(vararg p0: Record): Void? {
            recordDAO.delete(p0[0])
            return null
        }
    }
}