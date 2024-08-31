package com.task.userapp.data

import com.task.userapp.data.dtos.PostResponse
import com.task.userapp.data.dtos.UserResponse
import com.task.userapp.data.remote.DataService
import com.task.userapp.data.remote.base.NetworkResult
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
class DataRepositoryImpTest {
    private lateinit var underTest: DataRepositoryImp
    private lateinit var dataService: DataService


    @BeforeAll
    fun setUp() {
        dataService = mockk()
        underTest = DataRepositoryImp(dataService)
    }

    @Test
    fun `test fetch user data success`() = runTest {
        coEvery { dataService.getUsersData() } returns Response.success(UserResponse().apply {
            add(mockk())
            add(mockk())
        })
        val actualResult = underTest.fetchUsersData() as NetworkResult.Success
        assertEquals(2, actualResult.data.size)
        coVerify { dataService.getUsersData() }
    }

    @Test
    fun `test fetch post data success`() = runTest {
        coEvery { dataService.getPostsData() } returns Response.success(PostResponse().apply {
            add(mockk())
            add(mockk())
        })
        val actualResult = underTest.fetchPostsData() as NetworkResult.Success
        assertEquals(2, actualResult.data.size)
        coVerify { dataService.getPostsData() }
    }

    @Test
    fun `test fetch user data error`() = runTest {
        val errorMsg = "Request failed please try again"
        coEvery { dataService.getUsersData() } returns Response.error(400, errorMsg.toResponseBody())
        val actualResult = underTest.fetchUsersData() as NetworkResult.Error
        assertEquals(errorMsg, actualResult.error.message)
        coVerify { dataService.getUsersData() }
    }

    @Test
    fun `test fetch post data error`() = runTest {
        val errorMsg = "Request failed please try again"
        coEvery { dataService.getPostsData() } returns Response.error(400, errorMsg.toResponseBody())
        val actualResult = underTest.fetchPostsData() as NetworkResult.Error
        assertEquals(errorMsg, actualResult.error.message)
        coVerify { dataService.getPostsData() }
    }

    @AfterAll
    fun tearDown() {
        clearAllMocks()
    }
}
