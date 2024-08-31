package com.task.userapp.data

import com.task.userapp.data.dtos.PostResponse
import com.task.userapp.data.dtos.UserResponse
import com.task.userapp.data.remote.RemoteDataSource
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
    private lateinit var remoteDataSource: RemoteDataSource


    @BeforeAll
    fun setUp() {
        remoteDataSource = mockk()
        underTest = DataRepositoryImp(remoteDataSource)
    }

    @Test
    fun `test fetch user data success`() = runTest {
        coEvery { remoteDataSource.getUsersData() } returns Response.success(UserResponse().apply {
            add(mockk())
            add(mockk())
        })
        val actualResult = underTest.fetchUsersData() as NetworkResult.Success
        assertEquals(2, actualResult.data.size)
        coVerify { remoteDataSource.getUsersData() }
    }

    @Test
    fun `test fetch post data success`() = runTest {
        coEvery { remoteDataSource.getPostsData() } returns Response.success(PostResponse().apply {
            add(mockk())
            add(mockk())
        })
        val actualResult = underTest.fetchPostsData() as NetworkResult.Success
        assertEquals(2, actualResult.data.size)
        coVerify { remoteDataSource.getPostsData() }
    }

    @Test
    fun `test fetch user data error`() = runTest {
        val errorMsg = "Request failed please try again"
        coEvery { remoteDataSource.getUsersData() } returns Response.error(400, errorMsg.toResponseBody())
        val actualResult = underTest.fetchUsersData() as NetworkResult.Error
        assertEquals(errorMsg, actualResult.error.message)
        coVerify { remoteDataSource.getUsersData() }
    }

    @Test
    fun `test fetch post data error`() = runTest {
        val errorMsg = "Request failed please try again"
        coEvery { remoteDataSource.getPostsData() } returns Response.error(400, errorMsg.toResponseBody())
        val actualResult = underTest.fetchPostsData() as NetworkResult.Error
        assertEquals(errorMsg, actualResult.error.message)
        coVerify { remoteDataSource.getPostsData() }
    }

    @AfterAll
    fun tearDown() {
        clearAllMocks()
    }
}
