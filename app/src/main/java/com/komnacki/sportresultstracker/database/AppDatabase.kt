package com.komnacki.sportresultstracker.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import java.security.AccessControlContext

@Database(
        version = 1,
        entities = [(Record::class), (Sport::class), (User::class)])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO() : UserDAO
    abstract fun sportDAO() : SportDAO
    abstract fun recordDAO() : RecordDAO
}