package com.task.userapp.data.remote

import com.task.userapp.data.dtos.PostResponse
import com.task.userapp.data.dtos.UserResponse

interface DataService {
    fun getUsersData(): UserResponse
    fun getPostsData(): PostResponse
}