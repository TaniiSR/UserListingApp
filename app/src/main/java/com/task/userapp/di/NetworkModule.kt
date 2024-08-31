package com.task.userapp.di

import com.task.userapp.data.remote.service.DataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providesReposService(): DataService = DataService.createService()

    @Provides
    fun providesIoDispatcher() = Dispatchers.IO
}