package com.toren.news.presentation.news_detail

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.toren.news.presentation.news_detail.viewmodel.NewsDetailViewModel

@Composable
fun NewsDetailScreen(
    viewModel: NewsDetailViewModel = hiltViewModel(),
) {
    Row {
        Text(text = "Hello")
    }
}