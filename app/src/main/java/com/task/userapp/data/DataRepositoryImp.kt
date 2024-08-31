package com.task.userapp.data

import com.task.userapp.data.dtos.PostItem
import com.task.userapp.data.dtos.UserItem
import com.task.userapp.data.remote.RemoteDataSource
import com.task.userapp.data.remote.base.BaseRepo
import com.task.userapp.data.remote.base.NetworkResult

class DataRepositoryImp(private val remoteDataSource: RemoteDataSource) : BaseRepo(), DataRepository {
    override suspend fun fetchUsersData(): NetworkResult<List<UserItem>> {
        return safeApiCall { remoteDataSource.getUsersData() }
    }

    override suspend fun fetchPostsData(): NetworkResult<List<PostItem>> {
        return safeApiCall { remoteDataSource.getPostsData() }
    }
}