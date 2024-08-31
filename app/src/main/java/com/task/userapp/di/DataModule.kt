package com.task.userapp.di


import com.task.userapp.data.DataRepository
import com.task.userapp.data.DataRepositoryImp
import com.task.userapp.data.remote.RemoteDataSource
import com.task.userapp.data.remote.RemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideRemoteSource(remoteSource: RemoteDataSourceImp): RemoteDataSource

    @Binds
    @Singleton
    abstract fun provideDataRepository(dataRepository: DataRepositoryImp): DataRepository
}