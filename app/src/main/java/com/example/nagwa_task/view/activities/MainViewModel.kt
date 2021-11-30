package com.example.nagwa_task.view.activities

import androidx.lifecycle.ViewModel
import com.example.nagwa_task.di.DaggerAppComponent
import com.example.nagwa_task.repository.AppRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var appRepository: AppRepository

    private val compositeDisposable by lazy { CompositeDisposable() }

    init {
        DaggerAppComponent.create().inject(this)
        compositeDisposable.add(appRepository.fetchAttachments())
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}