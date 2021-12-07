package com.example.nagwa_task.repository

import com.example.nagwa_task.data.models.Attachment
import com.example.nagwa_task.data.network.APIEndPoint
import com.example.nagwa_task.di.DaggerAppComponent
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class AppRepository {

    @Inject
    lateinit var apiEndPoint: APIEndPoint;

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun getAttachments() : Observable<List<Attachment>> = apiEndPoint.getAttachments()

    fun downloadFile(attachment: Attachment) : Observable<Response<ResponseBody>> = apiEndPoint.downloadFile(attachment.url)

}