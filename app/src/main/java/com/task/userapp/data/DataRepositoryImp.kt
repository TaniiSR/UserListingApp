package com.task.userapp.data

import com.task.userapp.data.dtos.PostItem
import com.task.userapp.data.dtos.UserItem
import com.task.userapp.data.remote.DataService

class DataRepositoryImp(private val dataService: DataService) : DataRepository {
    override suspend fun fetchUsersData(): List<UserItem> {
        return dataService.getUsersData()
    }

    override suspend fun fetchPostsData(): List<PostItem> {
        return dataService.getPostsData()
    }
}