package com.toren.news.presentation.news_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.toren.news.domain.model.News
import com.toren.news.presentation.news_detail.viewmodel.NewsDetailViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetailScreen(
    news: News,
    viewModel: NewsDetailViewModel = hiltViewModel(),
) {
    val newsDetail = viewModel.state(news)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {

        GlideImage(
            model = newsDetail.urlToImage,
            contentDescription = "News Image",
            modifier = Modifier.padding(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp)
        )

        Text(
            text = newsDetail.title,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.headlineMedium
            )

    }
}

