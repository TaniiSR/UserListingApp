package com.task.userapp.data.remote

import com.task.userapp.data.dtos.PostResponse
import com.task.userapp.data.dtos.UserResponse
import retrofit2.Response

interface RemoteDataSource {
    fun getUsersData(): Response<UserResponse>
    fun getPostsData(): Response<PostResponse>
}