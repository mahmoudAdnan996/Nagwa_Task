package com.example.nagwa_task.view.activities

import android.os.Environment
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nagwa_task.data.models.Attachment
import com.example.nagwa_task.di.DaggerAppComponent
import com.example.nagwa_task.repository.AppRepository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import okio.buffer
import okio.sink
import org.reactivestreams.Subscriber
import retrofit2.Response
import java.io.File
import java.io.IOException
import javax.inject.Inject

class MainViewModel : ViewModel() {

    @Inject
    lateinit var appRepository: AppRepository

    private val compositeDisposable by lazy { CompositeDisposable() }

    private val _attachments by lazy { MutableLiveData<List<Attachment>>() }
    val attachments: LiveData<List<Attachment>>
        get() = _attachments

    private val _isLoading by lazy { MutableLiveData<Boolean>() }
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _hasError by lazy { MutableLiveData<Boolean>() }
    val hasError: LiveData<Boolean>
        get() = _hasError

    init {
        DaggerAppComponent.create().inject(this)
        _isLoading.postValue(true)
        compositeDisposable.add(fetchAttachments())
    }

    private fun fetchAttachments(): Disposable {
        return appRepository.getAttachments()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ attachmentsList ->
                _isLoading.postValue(false)
                if (attachmentsList != null && attachmentsList.isNotEmpty()) {
                    _attachments.postValue(attachmentsList)
                }
            },
                {
                    _hasError.postValue(true)
                    _isLoading.postValue(false)
                })
    }

    fun downloadFile(attachment: Attachment) {
        appRepository.downloadFile(attachment)
            .flatMap { responseBodyResponse ->
                Observable.create<File> { emitter ->

                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<File> {
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.d("downloadZipFile", "Error " + e.message)
                }
                override fun onComplete() {
                    Log.d("TAG", "onComplete: ")
                }
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(file: File) {
                    Log.d("downloadZipFile", "File downloaded to " + file.absolutePath)
                }
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}