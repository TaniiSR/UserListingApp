package com.task.userapp.presentation

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.task.userapp.data.remote.base.NetworkResult
import com.task.userapp.domain.model.PostModel
import com.task.userapp.domain.model.UserModel
import com.task.userapp.domain.usecase.GetDataUserPostUseCase
import com.task.userapp.presentation.ui.ViewType
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExperimentalCoroutinesApi
class MainActivityViewModelTest {
    private lateinit var underTest: MainActivityViewModel
    private val getDataUserPostUseCase: GetDataUserPostUseCase = mockk()
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.Unconfined

    @BeforeAll
    fun setUp() {
        underTest = MainActivityViewModel(
            getDataUserPostUseCase,
            ioDispatcher
        )
    }

    private fun provideParameters() = Stream.of(
        Arguments.of(
            NetworkResult.Error(
                error = Exception("error")
            ),
            ViewType.Error(
                errorMessage = "error"
            )
        ),
        Arguments.of(
            NetworkResult.Success(
                data = listOf(
                    UserModel(
                        albumId = 1,
                        name = "name",
                        url = "url",
                        userId = 1,
                        thumbnailUrl = "thumbnailUrl",
                        postList = listOf(
                            PostModel(
                                body = "body",
                                id = 1,
                                title = "title",
                                userId = 1
                            )
                        )
                    )
                )
            ),
            ViewType.Success(
                data = listOf(
                    UserModel(
                        albumId = 1,
                        name = "name",
                        url = "url",
                        userId = 1,
                        thumbnailUrl = "thumbnailUrl",
                        postList = listOf(
                            PostModel(
                                body = "body",
                                id = 1,
                                title = "title",
                                userId = 1
                            )
                        )
                    )
                )
            )
        )
    )

    @ParameterizedTest(name = "viewType is: {1} when getDataUserPostUseCase returns: {0}")
    @MethodSource("provideParameters")
    fun `test that viewType is updated with the correct value when getDataUserPostUseCase returns expected data`(
        data: NetworkResult<List<UserModel>>,
        expectedViewType: ViewType
    ) = runTest {
        coEvery {
            getDataUserPostUseCase()
        } returns data

        underTest.fetchData()
        testScheduler.advanceUntilIdle()
        underTest.uiState.test {
            val value = awaitItem()
            Truth.assertThat(value.viewType).isEqualTo(expectedViewType)
        }
    }

    @AfterAll
    fun tearDown() {
        clearAllMocks()
    }
}