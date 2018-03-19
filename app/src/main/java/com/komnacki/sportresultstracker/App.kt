package com.komnacki.sportresultstracker


import android.app.Application

import com.komnacki.sportresultstracker.database.AppComponent
import com.komnacki.sportresultstracker.database.AppModule
import com.komnacki.sportresultstracker.database.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(applicationContext))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

    fun appComponent(): AppComponent? {
        return appComponent
    }

    companion object {
        private lateinit var app: App

        fun app(): App {
            return app
        }
    }
}