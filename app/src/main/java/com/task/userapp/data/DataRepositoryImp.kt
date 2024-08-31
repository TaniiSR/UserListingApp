package com.task.userapp.data

import com.task.userapp.data.dtos.PostItem
import com.task.userapp.data.dtos.UserItem
import com.task.userapp.data.remote.DataService
import com.task.userapp.data.remote.base.BaseRepo
import com.task.userapp.data.remote.base.NetworkResult

class DataRepositoryImp(private val dataService: DataService) : BaseRepo(), DataRepository {
    override suspend fun fetchUsersData(): NetworkResult<List<UserItem>> {
        return safeApiCall { dataService.getUsersData() }
    }

    override suspend fun fetchPostsData(): NetworkResult<List<PostItem>> {
        return safeApiCall { dataService.getPostsData() }
    }
}