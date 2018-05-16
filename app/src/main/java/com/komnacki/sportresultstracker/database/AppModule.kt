package com.komnacki.sportresultstracker.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import android.content.Context
import android.util.Log
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    private val LOG_TAG = AppModule::class.java.name
    private val DATABASE_NAME = "database_SportResultTracker"

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        Log.d(LOG_TAG, "Provide new database")
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDAO(database: AppDatabase): UserDAO {
        Log.d(LOG_TAG, "Provide userDAO")
        return database.userDAO()
    }

    @Provides
    @Singleton
    fun provideSportDAO(database: AppDatabase): SportDAO {
        Log.d(LOG_TAG, "Provide sportDAO")
        return database.sportDAO()
    }

    @Provides
    @Singleton
    fun provideRecordDAO(database: AppDatabase): RecordDAO {
        Log.d(LOG_TAG, "Provide recordDAO")
        return database.recordDAO()
    }
}