package com.task.userapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.task.userapp.R
import com.task.userapp.domain.model.UserModel
import com.task.userapp.presentation.ui.composeViews.ErrorView
import com.task.userapp.presentation.ui.composeViews.LoadingView
import com.task.userapp.presentation.ui.composeViews.MainScreenView
import com.task.userapp.presentation.view.MainUIState
import com.task.userapp.presentation.view.ViewType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    uiState: MainUIState,
    onUserClicked: (user: UserModel) -> Unit,
    onRetryClicked: () -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.main_screen),
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surface,
                ),
            )
        }
    )
    { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (uiState.viewType) {
                is ViewType.Loading -> {
                    LoadingView()
                }

                is ViewType.Success -> {
                    MainScreenView(
                        userList = (uiState.viewType as ViewType.Success).data,
                        onUserClicked = onUserClicked
                    )
                }

                is ViewType.Error -> {
                    ErrorView(onRetryClicked = onRetryClicked)
                }
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MaterialTheme {
        MainScreen(
            uiState = MainUIState(
                viewType = ViewType.Success(
                    data = listOf(
                        UserModel(
                            name = "John Doe",
                            thumbnailUrl = "",
                            postList = listOf(),
                            albumId = 1,
                            userId = 2,
                            url = ""
                        )
                    )
                )
            ),
            onUserClicked = {},
            onRetryClicked = {}
        )
    }
}