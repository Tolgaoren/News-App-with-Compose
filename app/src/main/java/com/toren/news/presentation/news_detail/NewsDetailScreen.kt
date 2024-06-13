package com.toren.news.presentation.news_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = newsDetail.source,
                style = MaterialTheme.typography.titleSmall
            )
        }
        if (newsDetail.urlToImage.isNotEmpty()) {
            GlideImage(
                model = newsDetail.urlToImage,
                contentDescription = "News Image",
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(
            text = newsDetail.title,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.headlineMedium
            )
        Row (
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Text(
                text = newsDetail.publishedAt,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = newsDetail.description,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = newsDetail.content,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.bodyLarge)
        Text(
            text = newsDetail.author,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.End
        )
    }
}

