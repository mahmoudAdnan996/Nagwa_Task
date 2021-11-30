package com.example.nagwa_task.di

import com.example.nagwa_task.data.models.Attachment
import com.example.nagwa_task.data.network.APIEndPoint
import com.example.nagwa_task.data.network.ApiService
import com.example.nagwa_task.repository.AppRepository
import com.example.nagwa_task.view.adapter.AttachmentAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApi() : APIEndPoint = ApiService.getAPIClient()

    @Provides
    fun provideAppRepository() = AppRepository()

    @Provides
    fun providesAttachmentList() = ArrayList<Attachment>()

    @Provides
    fun provideAttachmentAdapter(attachments: ArrayList<Attachment>) : AttachmentAdapter = AttachmentAdapter(attachments)
}