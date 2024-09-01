package com.task.userapp.presentation.ui.composeViews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.task.userapp.R
import com.task.userapp.domain.model.UserModel
import com.task.userapp.utils.CombinedThemePreviewsWithBackground

@Composable
fun MainScreenView(
    userList: List<UserModel>,
    onUserClicked: (user: UserModel) -> Unit
) {
    LazyColumn {
        items(userList.size) { index ->
            UserItemView(user = userList[index], onUserClicked = onUserClicked)
        }
    }
}

@Composable
fun UserItemView(
    user: UserModel,
    onUserClicked: (user: UserModel) -> Unit
) {
    Row(modifier = Modifier
        .padding(start = 16.dp, top = 5.dp, bottom = 8.dp, end = 16.dp)
        .background(color = MaterialTheme.colorScheme.onSecondary)
        .fillMaxWidth()
        .clickable {
            onUserClicked(user)
        }
    ) {
        val matrix = ColorMatrix()
        matrix.setToSaturation(0F)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.thumbnailUrl)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterVertically)
                .size(48.dp)
                .clip(CircleShape),
            colorFilter = ColorFilter.colorMatrix(matrix)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp, top = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = stringResource(id = R.string.user_name) + " ${user.name}")
            Text(text = stringResource(id = R.string.post_count) + " ${user.postList.size}")
        }
    }
}

@CombinedThemePreviewsWithBackground
@Composable
fun ReportReasonPreview() {
    MaterialTheme {
        MainScreenView(
            userList = listOf(
                UserModel(
                    name = "John Doe",
                    thumbnailUrl = "",
                    postList = listOf(),
                    albumId = 1,
                    userId = 2,
                    url = ""
                )
            )
        ) {}
    }
}