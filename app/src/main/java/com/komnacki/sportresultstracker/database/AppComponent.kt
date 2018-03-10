package com.komnacki.sportresultstracker.database

import android.arch.persistence.room.Room
import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.komnacki.sportresultstracker.MainActivity
import com.komnacki.sportresultstracker.SportsListActivity
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent{
    fun inject(sportsListActivity: SportsListActivity)

}