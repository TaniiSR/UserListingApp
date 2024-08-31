package com.task.userapp.domain.mapper

import com.task.userapp.data.dtos.PostItem
import com.task.userapp.data.dtos.UserItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExperimentalCoroutinesApi
class UserPostToSingleModelMapperTest {
    private val underTest = UserPostToSingleModelMapper()

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

        val result = underTest(mockUserData, mockPostData)
        assert(result.isNotEmpty())
        assert(result.size == 2)
        assert(result.first().userId == 2)
        assert(result.last().userId == 1)
    }
}