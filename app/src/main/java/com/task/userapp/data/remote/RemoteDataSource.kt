package com.task.userapp.data.remote

import com.task.userapp.data.dtos.PostResponse
import com.task.userapp.data.dtos.UserResponse
import com.task.userapp.data.remote.base.NetworkResult

interface RemoteDataSource {
    suspend fun getUsersData(): NetworkResult<UserResponse>
    suspend fun getPostsData(): NetworkResult<PostResponse>
}