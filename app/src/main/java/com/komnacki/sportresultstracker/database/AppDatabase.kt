package com.komnacki.sportresultstracker.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import java.security.AccessControlContext

@Database(
        version = 1,
        entities = [(Record::class), (Sport::class), (User::class)])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDAO() : UserDAO
    abstract fun sportDAO() : SportDAO
    abstract fun recordDAO() : RecordDAO


//    public var databaseCallback = object : RoomDatabase.Callback() {
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//
//        }
//    }
//
//    inner class PopulateDbAsync(db: AppDatabase) : AsyncTask<Void, Void, Void>() {
//        val userDAO: UserDAO = db.userDAO()
//        val users = arrayOf<User>()
//
//        override fun doInBackground(vararg p0: Void?): Void? {
//            userDAO.deleteAll()
//
//            users.forEach {
//                var user = User()
//                user.name = "Kamil"
//                user.sportsAmount = 55
//                userDAO.insert(user)
//            }
//
//            return null
//        }
//
//    }
}