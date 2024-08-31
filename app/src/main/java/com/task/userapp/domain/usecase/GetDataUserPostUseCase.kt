package com.task.userapp.domain.usecase

import com.task.userapp.data.DataRepository
import com.task.userapp.data.dtos.PostItem
import com.task.userapp.data.dtos.UserItem
import com.task.userapp.data.remote.base.NetworkResult
import com.task.userapp.domain.mapper.UserPostToSingleModelMapper
import com.task.userapp.domain.model.UserModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlin.coroutines.coroutineContext

class GetDataUserPostUseCase(
    private val dataRepository: DataRepository,
    private val userPostToSingleModelMapper: UserPostToSingleModelMapper
) {
    suspend operator fun invoke(): NetworkResult<List<UserModel>> {
        val userList = CoroutineScope(coroutineContext).async {
            dataRepository.fetchUsersData()
        }
        val postList = CoroutineScope(coroutineContext).async {
            dataRepository.fetchPostsData()
        }
        return if (userList.await() is NetworkResult.Success && postList.await() is NetworkResult.Success) {
            NetworkResult.Success(
                data = userPostToSingleModelMapper(
                    (userList.await() as NetworkResult.Success<List<UserItem>>).data,
                    (postList.await() as NetworkResult.Success<List<PostItem>>).data
                )
            )
        } else {
            if (userList.await() is NetworkResult.Error) {
                NetworkResult.Error((userList.await() as NetworkResult.Error).error)
            } else {
                NetworkResult.Error((postList.await() as NetworkResult.Error).error)
            }
        }
    }
}