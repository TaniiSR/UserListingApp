package com.task.userapp.data.remote.service

import com.task.userapp.data.dtos.PostResponse
import com.task.userapp.data.dtos.UserResponse
import retrofit2.Response

interface DataService {
    fun getUsersData(): Response<UserResponse>
    fun getPostsData(): Response<PostResponse>
}