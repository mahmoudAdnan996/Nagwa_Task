package com.example.nagwa_task.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nagwa_task.data.models.Attachment
import com.example.nagwa_task.data.network.APIEndPoint
import com.example.nagwa_task.di.DaggerAppComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppRepository {

    @Inject
    lateinit var apiEndPoint: APIEndPoint;

    init {
        DaggerAppComponent.create().inject(this)
    }

    private val _attachments by lazy { MutableLiveData<List<Attachment>>() }
    val attachments: LiveData<List<Attachment>>
        get() = _attachments

    private val _isLoading by lazy { MutableLiveData<Boolean>() }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _hasError by lazy { MutableLiveData<Boolean>() }
    val hasError: LiveData<Boolean>
        get() = _hasError

    private fun getAttachments() : Disposable{
        _isLoading.postValue(true);
        return apiEndPoint.getAttachments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ attachmentsList ->
                _isLoading.postValue(false)
                if (attachmentsList != null && attachmentsList.isNotEmpty()) {
                    _attachments.postValue(attachmentsList)
                }
            },
                {
                    Log.e("getTrendingQuery()", "Database error: ${it.message}")
                    _hasError.postValue(true)
                    _isLoading.postValue(false)
                })
    }

    fun fetchAttachments () : Disposable = getAttachments()
}