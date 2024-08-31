package com.task.userapp.data

import com.task.userapp.data.dtos.PostItem
import com.task.userapp.data.dtos.UserItem

interface DataRepository {
    suspend fun fetchUsersData(): List<UserItem>?
    suspend fun fetchPostsData(): List<PostItem>?
}