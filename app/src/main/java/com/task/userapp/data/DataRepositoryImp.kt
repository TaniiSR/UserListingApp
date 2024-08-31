package com.task.userapp.data

import com.task.userapp.data.dtos.PostItem
import com.task.userapp.data.dtos.UserItem
import com.task.userapp.data.remote.DataService

class DataRepositoryImp(private val dataService: DataService) : DataRepository {
    override suspend fun fetchUsersData(): List<UserItem>? {
        val response = dataService.getUsersData()
        return if (response.isSuccessful) {
            response.body()
        } else {
            emptyList()
        }
    }

    override suspend fun fetchPostsData(): List<PostItem>? {
        val response = dataService.getPostsData()
        return if (response.isSuccessful) {
            response.body()
        } else {
            emptyList()
        }
    }
}