package com.task.userapp.data.dtos

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String? = null,
    @SerializedName("albumId")
    val albumId: Int? = null,
    @SerializedName("userId")
    val userId: Int? = null
)
