package com.komnacki.sportresultstracker.database

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(userRepository: UserRepository)
    fun inject(sportRepository: SportRepository)
    //fun inject(sportsListActivity: SportsListActivity)
}