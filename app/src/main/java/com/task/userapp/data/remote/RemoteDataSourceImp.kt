package com.task.userapp.data.remote

import com.task.userapp.data.dtos.PostResponse
import com.task.userapp.data.dtos.UserResponse
import com.task.userapp.data.remote.base.BaseRepo
import com.task.userapp.data.remote.base.NetworkResult
import com.task.userapp.data.remote.service.DataService
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val apiService: DataService) : BaseRepo(),
    RemoteDataSource {
    override suspend fun getUsersData(): NetworkResult<UserResponse> {
        return safeApiCall { apiService.getUsers() }
    }

    override suspend fun getPostsData(): NetworkResult<PostResponse> {
        return safeApiCall { apiService.getPosts() }
    }
}