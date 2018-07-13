package com.komnacki.sportresultstracker.database

import android.arch.lifecycle.LiveData
import com.komnacki.sportresultstracker.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordRepository {
    val LOG_TAG = RecordRepository::class.java.name

    @Inject
    lateinit var recordDAO: RecordDAO

    init {
        App.app().appComponent()?.inject(this)
    }

    fun insert(record: Record) {
        object : DAOAsyncProcessor<Unit>(null) {
            override fun doAsync() {
                recordDAO.insert(record)
            }
        }.start()
    }

    fun update(record: Record){
        object : DAOAsyncProcessor<Unit>(null) {
            override fun doAsync() {
                recordDAO.update(record)
            }
        }.start()
    }

    fun delete(record: Record) {
        object : DAOAsyncProcessor<Unit>(null) {
            override fun doAsync() {
                recordDAO.delete(record)
            }
        }.start()
    }

    fun getAll(sportId: Long): LiveData<List<Record>> {
        return recordDAO.getAll(sportId)
    }

    fun get(id: Long): LiveData<Record> {
        return recordDAO.get(id)
    }
}