package com.task.userapp.data

import com.task.userapp.data.dtos.PostResponse
import com.task.userapp.data.dtos.UserResponse
import com.task.userapp.data.remote.DataService
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
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
    private lateinit var dataService: DataService


    @BeforeAll
    fun setUp() {
        dataService = mockk()
        underTest = DataRepositoryImp(dataService)
    }

    @Test
    fun `test fetch user data success`() = runTest {
        coEvery { dataService.getUsersData() } returns UserResponse().apply {
            add(mockk())
            add(mockk())
        }
        val actualResult = underTest.fetchUsersData()
        assertEquals(2, actualResult.size)
        coVerify { dataService.getUsersData() }
    }

    @Test
    fun `test fetch post data success`() = runTest {
        coEvery { dataService.getPostsData() } returns PostResponse().apply {
            add(mockk())
            add(mockk())
        }
        val actualResult = underTest.fetchPostsData()
        assertEquals(2, actualResult.size)
        coVerify { dataService.getPostsData() }
    }

    @AfterAll
    fun tearDown() {
        clearAllMocks()
    }
}
