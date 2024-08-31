package com.task.userapp.data

import com.task.userapp.data.dtos.PostResponse
import com.task.userapp.data.dtos.UserResponse
import com.task.userapp.data.remote.RemoteDataSource
import com.task.userapp.data.remote.base.NetworkResult
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

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
        coEvery { remoteDataSource.getUsersData() } returns NetworkResult.Success(data = UserResponse().apply {
            add(mockk())
            add(mockk())
        })
        val actualResult = underTest.fetchUsersData() as NetworkResult.Success
        assertEquals(2, actualResult.data.size)
        coVerify { remoteDataSource.getUsersData() }
    }

    @Test
    fun `test fetch post data success`() = runTest {
        coEvery { remoteDataSource.getPostsData() } returns NetworkResult.Success(data = PostResponse().apply {
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
        coEvery { remoteDataSource.getUsersData() } returns mockk<NetworkResult.Error> {
            every { error } returns mockk {
                every { message } returns errorMsg
            }
        }
        val actualResult = underTest.fetchUsersData() as NetworkResult.Error
        assertEquals(errorMsg, actualResult.error.message)
        coVerify { remoteDataSource.getUsersData() }
    }

    @Test
    fun `test fetch post data error`() = runTest {
        val errorMsg = "Request failed please try again"
        coEvery { remoteDataSource.getPostsData() } returns mockk<NetworkResult.Error> {
            every { error } returns mockk {
                every { message } returns errorMsg
            }
        }
        val actualResult = underTest.fetchPostsData() as NetworkResult.Error
        assertEquals(errorMsg, actualResult.error.message)
        coVerify { remoteDataSource.getPostsData() }
    }

    @AfterAll
    fun tearDown() {
        clearAllMocks()
    }
}
