package com.task.userapp.domain.usecase

import com.task.userapp.data.DataRepository
import com.task.userapp.data.dtos.PostItem
import com.task.userapp.data.dtos.UserItem
import com.task.userapp.data.remote.base.NetworkResult
import com.task.userapp.domain.mapper.UserPostToSingleModelMapper
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExperimentalCoroutinesApi
class GetDataUserPostUseCaseTest {
    private lateinit var underTest: GetDataUserPostUseCase
    private lateinit var dataRepository: DataRepository
    private lateinit var userPostToSingleModelMapper: UserPostToSingleModelMapper

    @BeforeAll
    fun setUp() {
        dataRepository = mockk()
        userPostToSingleModelMapper = mockk()
        underTest = GetDataUserPostUseCase(dataRepository, userPostToSingleModelMapper)
    }

    @Test
    fun `test that invoke returns list of UserModelList`() = runTest {
        val mockUserData = listOf(
            UserItem(
                name = "name",
                url = "url",
                userId = 2,
                thumbnailUrl = "thumbnail",
                albumId = 1
            ),
            UserItem(
                name = "name",
                url = "url",
                userId = 1,
                thumbnailUrl = "thumbnail",
                albumId = 1
            )
        )
        val mockPostData = listOf(
            PostItem(
                body = "body",
                id = 2,
                title = "title",
                userId = 2
            ),
            PostItem(
                body = "body",
                id = 1,
                title = "title",
                userId = 1
            )
        )
        // Given
        coEvery { dataRepository.fetchPostsData() } returns NetworkResult.Success(data = mockPostData)
        coEvery { dataRepository.fetchUsersData() } returns NetworkResult.Success(data = mockUserData)
        coEvery {
            userPostToSingleModelMapper(
                userList = mockUserData,
                postList = mockPostData
            )
        } returns listOf(
            mockk(),
            mockk()
        )

        // When
        val actualResult = underTest() as NetworkResult.Success
        // Then
        assert(actualResult.data.isNotEmpty())
        assert(actualResult.data.size == 2)
        coVerify { dataRepository.fetchPostsData() }
        coVerify { dataRepository.fetchPostsData() }
        coVerify { userPostToSingleModelMapper(mockUserData, mockPostData) }
    }

    @Test
    fun `test that invoke returns Error with post list`() = runTest {
        val errorMsg = "Request failed please try again"
        val mockUserData = listOf(
            UserItem(
                name = "name",
                url = "url",
                userId = 2,
                thumbnailUrl = "thumbnail",
                albumId = 1
            ),
            UserItem(
                name = "name",
                url = "url",
                userId = 1,
                thumbnailUrl = "thumbnail",
                albumId = 1
            )
        )
        val mockPostData = listOf(
            PostItem(
                body = "body",
                id = 2,
                title = "title",
                userId = 2
            ),
            PostItem(
                body = "body",
                id = 1,
                title = "title",
                userId = 1
            )
        )
        // Given
        coEvery { dataRepository.fetchPostsData() } returns mockk<NetworkResult.Error> {
            every { error } returns mockk {
                every { message } returns errorMsg
            }
        }
        coEvery { dataRepository.fetchUsersData() } returns NetworkResult.Success(data = mockUserData)
        coEvery {
            userPostToSingleModelMapper(
                userList = mockUserData,
                postList = mockPostData
            )
        } returns listOf(
            mockk(),
            mockk()
        )

        // When
        val actualResult = underTest.invoke() as NetworkResult.Error
        // Then
        assert(actualResult.error.message == errorMsg)
    }

    @Test
    fun `test that invoke returns Error with user list`() = runTest {
        val errorMsg = "Request failed please try again"
        val mockUserData = listOf(
            UserItem(
                name = "name",
                url = "url",
                userId = 2,
                thumbnailUrl = "thumbnail",
                albumId = 1
            ),
            UserItem(
                name = "name",
                url = "url",
                userId = 1,
                thumbnailUrl = "thumbnail",
                albumId = 1
            )
        )
        val mockPostData = listOf(
            PostItem(
                body = "body",
                id = 2,
                title = "title",
                userId = 2
            ),
            PostItem(
                body = "body",
                id = 1,
                title = "title",
                userId = 1
            )
        )
        // Given
        coEvery { dataRepository.fetchPostsData() } returns NetworkResult.Success(data = mockPostData)
        coEvery { dataRepository.fetchUsersData() } returns mockk<NetworkResult.Error> {
            every { error } returns mockk {
                every { message } returns errorMsg
            }
        }
        coEvery {
            userPostToSingleModelMapper(
                userList = mockUserData,
                postList = mockPostData
            )
        } returns listOf(
            mockk(),
            mockk()
        )

        // When
        val actualResult = underTest.invoke() as NetworkResult.Error
        // Then
        assert(actualResult.error.message == errorMsg)
    }

    @AfterAll
    fun tearDown() {
        clearAllMocks()
    }
}