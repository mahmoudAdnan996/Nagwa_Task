package com.example.nagwa_task.di

import com.example.nagwa_task.repository.AppRepository
import com.example.nagwa_task.view.activities.MainActivity
import com.example.nagwa_task.view.activities.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(appRepository: AppRepository)

    fun inject(mainViewModel: MainViewModel)

    fun inject(mainActivity: MainActivity)
}