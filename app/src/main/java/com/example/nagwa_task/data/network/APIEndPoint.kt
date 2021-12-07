package com.example.nagwa_task.data.network

import com.example.nagwa_task.data.models.Attachment
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface APIEndPoint {

    @GET("/v1/0e4c7f2c-822b-4747-bf72-59ce4d4e2683")
    fun getAttachments() : Observable<List<Attachment>>

    @Streaming
    @GET
    fun downloadFile(@Url url: String) : Observable<Response<ResponseBody>>
}