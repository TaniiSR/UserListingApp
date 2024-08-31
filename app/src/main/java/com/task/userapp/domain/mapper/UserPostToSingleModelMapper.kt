package com.task.userapp.domain.mapper

import com.task.userapp.data.dtos.PostItem
import com.task.userapp.data.dtos.UserItem
import com.task.userapp.domain.model.PostModel
import com.task.userapp.domain.model.UserModel


class UserPostToSingleModelMapper {
    operator fun invoke(
        userList: List<UserItem>,
        postList: List<PostItem>
    ): List<UserModel> {
        val usersList = mutableListOf<UserModel>()
        userList.forEach { userItem ->
            val userPostList = postList.filter { it.userId == userItem.userId }
            usersList.add(
                UserModel(
                    albumId = userItem.albumId ?: 0,
                    userId = userItem.userId ?: 0,
                    name = userItem.name.orEmpty(),
                    url = userItem.url.orEmpty(),
                    thumbnailUrl = userItem.thumbnailUrl.orEmpty(),
                    postList = userPostList.map { post ->
                        PostModel(
                            post.userId ?: 0,
                            post.id ?: 0,
                            post.title.orEmpty(),
                            post.body.orEmpty()
                        )
                    }
                )
            )
        }
        return usersList
    }
}
