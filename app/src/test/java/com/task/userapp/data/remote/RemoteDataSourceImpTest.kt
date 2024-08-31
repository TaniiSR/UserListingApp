package com.task.userapp.data.remote

import com.task.userapp.data.dtos.PostResponse
import com.task.userapp.data.dtos.UserResponse
import com.task.userapp.data.remote.base.NetworkResult
import com.task.userapp.data.remote.service.DataService
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import retrofit2.Response

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExperimentalCoroutinesApi
class RemoteDataSourceImpTest {

    private lateinit var underTest: RemoteDataSourceImp
    private lateinit var apiService: DataService


    @BeforeAll
    fun setUp() {
        apiService = mockk()
        underTest = RemoteDataSourceImp(apiService)
    }

    @Test
    fun `test fetch user data success`() = runTest {
        coEvery { apiService.getUsersData() } returns Response.success(UserResponse().apply {
            add(mockk())
            add(mockk())
        })
        val actualResult = underTest.getUsersData() as NetworkResult.Success
        assertEquals(2, actualResult.data.size)
        coVerify { apiService.getUsersData() }
    }

    @Test
    fun `test fetch post data success`() = runTest {
        coEvery { apiService.getPostsData() } returns Response.success(PostResponse().apply {
            add(mockk())
            add(mockk())
        })
        val actualResult = underTest.getUsersData() as NetworkResult.Success
        assertEquals(2, actualResult.data.size)
        coVerify { apiService.getPostsData() }
    }

    @Test
    fun `test fetch user data error`() = runTest {
        val errorMsg = "Request failed please try again"
        coEvery { apiService.getUsersData() } returns Response.error(400, errorMsg.toResponseBody())
        val actualResult = underTest.getUsersData() as NetworkResult.Error
        assertEquals(errorMsg, actualResult.error.message)
        coVerify { apiService.getUsersData() }
    }

    @Test
    fun `test fetch post data error`() = runTest {
        val errorMsg = "Request failed please try again"
        coEvery { apiService.getPostsData() } returns Response.error(400, errorMsg.toResponseBody())
        val actualResult = underTest.getPostsData() as NetworkResult.Error
        assertEquals(errorMsg, actualResult.error.message)
        coVerify { apiService.getPostsData() }
    }

    @AfterAll
    fun tearDown() {
        clearAllMocks()
    }
}