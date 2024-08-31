package com.task.userapp.data.remote.service

import com.task.userapp.BuildConfig
import com.task.userapp.data.dtos.PostResponse
import com.task.userapp.data.dtos.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DataService {
    @GET("SharminSirajudeen/test_resources/users")
    suspend fun getUsers(): Response<UserResponse>

    @GET("SharminSirajudeen/test_resources/posts")
    suspend fun getPosts(): Response<PostResponse>

    companion object {
        private const val BASE_URL = "https://my-json-server.typicode.com/"
        fun createService(): DataService {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DataService::class.java)
        }
    }
}