package com.task.userapp.data.dtos

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("body")
    val body: String? = null,
    @SerializedName("userId")
    val userId: Int? = null,
    @SerializedName("id")
    val id: Int? = null
)
