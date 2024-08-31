package com.task.userapp.data

import com.task.userapp.data.dtos.PostItem
import com.task.userapp.data.dtos.UserItem
import com.task.userapp.data.remote.base.NetworkResult

interface DataRepository {
    suspend fun fetchUsersData(): NetworkResult<List<UserItem>>
    suspend fun fetchPostsData(): NetworkResult<List<PostItem>>
}