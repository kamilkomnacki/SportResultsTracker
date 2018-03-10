package com.komnacki.sportresultstracker.database

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    val DATABASE_NAME = "database_SportResultTracker"

    @Provides
    @Singleton
    fun provideContext(): Context{
        return context
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase{
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDAO(database: AppDatabase): UserDAO{
        return database.userDAO()
    }
}