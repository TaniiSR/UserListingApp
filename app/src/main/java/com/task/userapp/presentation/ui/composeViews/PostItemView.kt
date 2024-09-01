package com.task.userapp.presentation.ui.composeViews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.task.userapp.domain.model.PostModel

@Composable
fun PostItemView(postModel: PostModel) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp)
            .background(color = MaterialTheme.colorScheme.onSecondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = postModel.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
        Text(
            text = postModel.body,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)

        )
    }
}

@Preview
@Composable
fun PostItemViewPreview() {
    MaterialTheme {
        PostItemView(
            PostModel(
                id = 1,
                userId = 2,
                body = "voluptatem cumque tenetur consequatur expedita ipsum nemo quia explicabo\\naut eum minima",
                title = "consequuntur deleniti eos quia temporibus ab aliquid at"
            )
        )
    }
}