package com.task.userapp.domain.model

data class UserModel(
    val albumId: Int,
    val userId: Int,
    val name: String,
    val url: String,
    val thumbnailUrl: String,
    val postList: List<PostModel>
)
